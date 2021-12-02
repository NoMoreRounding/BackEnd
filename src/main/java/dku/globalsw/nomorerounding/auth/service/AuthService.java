package dku.globalsw.nomorerounding.auth.service;

import dku.globalsw.nomorerounding.auth.dto.request.LoginRequest;
import dku.globalsw.nomorerounding.auth.dto.request.ResetRequest;
import dku.globalsw.nomorerounding.auth.dto.request.SignUpRequest;
import dku.globalsw.nomorerounding.auth.util.PasswordUtil;
import dku.globalsw.nomorerounding.car.domain.entity.Car;
import dku.globalsw.nomorerounding.car.service.CarService;
import dku.globalsw.nomorerounding.auth.exception.InvalidPasswordException;
import dku.globalsw.nomorerounding.user.domain.entity.User;
import dku.globalsw.nomorerounding.car.dto.mapper.CarMapper;
import dku.globalsw.nomorerounding.user.dto.mapper.UserMapper;
import dku.globalsw.nomorerounding.user.dto.request.UserRequest;
import dku.globalsw.nomorerounding.user.dto.response.StringResponse;
import dku.globalsw.nomorerounding.auth.token.TokenResponse;
import dku.globalsw.nomorerounding.user.dto.response.UserResponse;
import dku.globalsw.nomorerounding.auth.exception.CoreException;
import dku.globalsw.nomorerounding.auth.exception.ExpiredTokenException;
import dku.globalsw.nomorerounding.auth.exception.InvalidTokenException;
import dku.globalsw.nomorerounding.auth.exception.InvalidUserException;
import dku.globalsw.nomorerounding.auth.exception.UpdateException;
import dku.globalsw.nomorerounding.user.service.UserService;
import dku.globalsw.nomorerounding.util.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final CarService carService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public String getTokenByHeader(HttpServletRequest httpServletRequest) {
        String token = "";
        try {
            String authenticationHeader = httpServletRequest.getHeader("Authorization");

            if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer")) {
                throw new InvalidTokenException();
            }

            token = authenticationHeader.replace("Bearer", "");
            if (token.length() == 0) {
                throw new InvalidTokenException();
            }
        } catch (InvalidTokenException invalidTokenException) {
            throw new ExpiredTokenException();
        } catch (Exception exception) {
            throw new CoreException("토큰 정보를 가져올 수 없습니다.");
        }

        return token;
    }

    public UserResponse getUserInfoByToken(String token) {
        if (jwtUtil.isTokenExpired(token)) {
            throw new ExpiredTokenException();
        }

        User savedUser;
        try {
            Long userId = jwtUtil.getUserIdByToken(token);
            savedUser = userService.findById(userId);
        } catch (Exception exception) {
            throw new InvalidUserException();
        }

        return UserMapper.INSTANCE.toDto(savedUser);
    }

    public TokenResponse generateTokenByRefreshToken(String refreshToken) {
        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new ExpiredTokenException();
        }

        Long userId = jwtUtil.getUserIdByToken(refreshToken);
        User savedUser = userService.findById(userId);

        TokenResponse tokenResponse = jwtUtil.generateToken(savedUser.getId(), savedUser.getEmail(),
            savedUser.getAuthority());
        savedUser.setAccessToken(tokenResponse.getAccessToken());
        savedUser.setRefreshToken(tokenResponse.getRefreshToken());
        userService.save(savedUser);

        return tokenResponse;
    }

    public String encodePassword(String password) {

        String encoded = passwordEncoder.encode(password);

        return encoded;
    }

    @Transactional
    public UserResponse login(LoginRequest loginRequest) {
        UserResponse userResponse = null;

        User user = userService.findUserByLoginId(loginRequest.getLoginId());
        if (isPasswordMatches(loginRequest.getPassword(), user)) {

            TokenResponse tokenResponse = jwtUtil.generateToken(user.getId(),
                user.getEmail(),
                user.getAuthority());

            user.setAccessToken(tokenResponse.getAccessToken());
            user.setRefreshToken(tokenResponse.getRefreshToken());

            userResponse = UserMapper.INSTANCE.toDto(user);
            userResponse.setTokenResponse(tokenResponse);
            userResponse.setCarResponse(CarMapper.INSTANCE.toDto(user.getCar()));
        } else {
            throw new InvalidPasswordException();
        }

        return userResponse;
    }

    @Transactional
    public UserResponse signUp (SignUpRequest signUpRequest) {

        UserResponse userResponse = null;

        userService.isSavedUser(signUpRequest.getCarNumber(), signUpRequest.getLoginId(), signUpRequest.getEmail());

        User user = UserMapper.INSTANCE.requestToEntity(signUpRequest);

        user.setPassword(encodePassword(signUpRequest.getPassword()));

        Car car = Car.builder()
            .carNumber(signUpRequest.getCarNumber())
            .pregnant(signUpRequest.isPregnant())
            .compactCar(signUpRequest.isCompactCar())
            .electric(signUpRequest.isElectric())
            .disabled(signUpRequest.isDisabled()).build();

        Car savedCar = carService.save(car);

        user.setCar(savedCar);
        User savedUser = userService.save(user);

        TokenResponse tokenResponse = jwtUtil.generateToken(savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getAuthority());

        savedUser.setAccessToken(tokenResponse.getAccessToken());
        savedUser.setRefreshToken(tokenResponse.getRefreshToken());

        userResponse = UserMapper.INSTANCE.toDto(savedUser);
        userResponse.setTokenResponse(tokenResponse);
        userResponse.setCarResponse(CarMapper.INSTANCE.toDto(savedUser.getCar()));

        return userResponse;
    }

    public boolean isPasswordMatches(String password, User user) {
        if (passwordEncoder.matches(password, user.getPassword())) {
            return true;
        } else {
            throw new InvalidUserException();
        }
    }

    public StringResponse resetPassword(ResetRequest resetRequest) {
        User user = userService.findUserByLoginIdAndEmail(
            resetRequest.getLoginId(), resetRequest.getEmail());

        String randomPw = PasswordUtil.randomPw();

        mailService.sendMail(user.getName(), user.getEmail(), randomPw);

        user.setPassword(encodePassword(randomPw));

        userService.save(user);

        return new StringResponse("임시 비밀번호가 포함된 이메일을 발송하였습니다");
    }

    public User findUserByHttpServlet(HttpServletRequest httpServletRequest) {
        String token = getTokenByHeader(httpServletRequest);

        if (jwtUtil.isTokenExpired(token)) {
            throw new ExpiredTokenException();
        }

        User savedUser;
        try {
            Long userId = jwtUtil.getUserIdByToken(token);
            savedUser = userService.findById(userId);
        } catch (Exception exception) {
            throw new InvalidUserException();
        }

        return savedUser;
    }

    public UserResponse update(UserRequest userRequest, HttpServletRequest httpServletRequest) {

        UserResponse userResponse = null;

        User user = findUserByHttpServlet(httpServletRequest);

        if (userRequest.equals(new UserRequest())) {
            throw new UpdateException();
        } else {
            String password = userRequest.getPassword();
            String name = userRequest.getName();
            String email = userRequest.getEmail();
            String carNumber = userRequest.getCarNumber();
            Boolean pregnant = userRequest.getPregnant();
            Boolean compactCar = userRequest.getCompactCar();
            Boolean electric = userRequest.getElectric();
            Boolean disabled = userRequest.getDisabled();
            Car userCar = user.getCar();

            if (!passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(encodePassword(password));
            }

            if (!name.equals(user.getName())) {
                user.setName(name);
            }

            if (!email.equals(user.getEmail())) {
                user.setEmail(email);
            }

            if (!carNumber.equals(userCar.getCarNumber())) {
                userCar.setCarNumber(carNumber);
            }

            if (pregnant != userCar.isPregnant()) {
                userCar.setPregnant(pregnant);
            }

            if (compactCar != userCar.isCompactCar()) {
                userCar.setCompactCar(compactCar);
            }

            if (electric != userCar.isElectric()) {
                userCar.setElectric(electric);
            }

            if (disabled != userCar.isDisabled()) {
                userCar.setDisabled(disabled);
            }

            Car savedCar = carService.save(userCar);
            user.setCar(savedCar);
            User savedUser = userService.save(user);

            TokenResponse tokenResponse = jwtUtil.generateToken(savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getAuthority());

            savedUser.setAccessToken(tokenResponse.getAccessToken());
            savedUser.setRefreshToken(tokenResponse.getRefreshToken());

            userResponse = UserMapper.INSTANCE.toDto(savedUser);
            userResponse.setTokenResponse(tokenResponse);
            userResponse.setCarResponse(CarMapper.INSTANCE.toDto(savedUser.getCar()));
        }

        return userResponse;
    }

    public UserResponse info(HttpServletRequest httpServletRequest) {

        UserResponse userResponse = null;

        User user = findUserByHttpServlet(httpServletRequest);

        TokenResponse tokenResponse = new TokenResponse(user.getAccessToken(),
            user.getRefreshToken());

        userResponse = UserMapper.INSTANCE.toDto(user);
        userResponse.setTokenResponse(tokenResponse);
        userResponse.setCarResponse(CarMapper.INSTANCE.toDto(user.getCar()));

        return userResponse;
    }
}
