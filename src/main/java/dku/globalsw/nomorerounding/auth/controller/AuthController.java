package dku.globalsw.nomorerounding.auth.controller;

import dku.globalsw.nomorerounding.auth.token.TokenRequest;
import dku.globalsw.nomorerounding.auth.token.TokenResponse;
import dku.globalsw.nomorerounding.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> reIssueToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok().body(authService.generateTokenByRefreshToken(tokenRequest.getRefreshToken()));
    }
}
