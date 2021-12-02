package dku.globalsw.nomorerounding.user.service;

import dku.globalsw.nomorerounding.user.domain.exception.DuplicatedUserException;
import dku.globalsw.nomorerounding.auth.exception.InvalidUserException;
import dku.globalsw.nomorerounding.car.service.CarService;
import dku.globalsw.nomorerounding.user.dto.response.StringResponse;
import dku.globalsw.nomorerounding.user.domain.repository.UserRepository;
import dku.globalsw.nomorerounding.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CarService carService;

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    public void isSavedUser(String carNumber, String loginId, String email) throws DuplicatedUserException {
        if (carService.isSavedCar(carNumber) || existsByLoginId(loginId) || existsByEmail(email)) {
            throw new DuplicatedUserException();
        }
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserByLoginId(String loginId) {
        return userRepository.findUserByLoginId(loginId).orElseThrow(
            InvalidUserException::new);
    }

    public User findUserByLoginIdAndEmail(String loginId, String email) {
        return userRepository.findUserByLoginIdAndEmail(loginId, email)
            .orElseThrow(InvalidUserException::new);
    }

    public StringResponse findLoginIdByEmail(String email) {
        StringResponse stringResponse = new StringResponse(
            userRepository.findUserByEmail(email).orElseThrow(InvalidUserException::new)
                .getLoginId());

        return stringResponse;
    }

    public Boolean existsByLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
