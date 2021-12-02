package dku.globalsw.nomorerounding.user.controller;

import dku.globalsw.nomorerounding.auth.dto.request.LoginRequest;
import dku.globalsw.nomorerounding.auth.dto.request.ResetRequest;
import dku.globalsw.nomorerounding.auth.dto.request.SignUpRequest;
import dku.globalsw.nomorerounding.auth.service.AuthService;
import dku.globalsw.nomorerounding.user.dto.response.StringResponse;
import dku.globalsw.nomorerounding.user.dto.request.UserRequest;
import dku.globalsw.nomorerounding.user.dto.response.UserResponse;
import dku.globalsw.nomorerounding.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {

        return ResponseEntity.ok().body(authService.signUp(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/reset")
    public ResponseEntity<StringResponse> resetPassword(@Valid @RequestBody ResetRequest resetRequest) {
        return ResponseEntity.ok().body(authService.resetPassword(resetRequest));
    }

    @GetMapping("/findId/{email}")
    public ResponseEntity<StringResponse> findLoginId(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(userService.findLoginIdByEmail(email));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest userRequest,
        HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(authService.update(userRequest, httpServletRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> info(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(authService.info(httpServletRequest));
    }
}
