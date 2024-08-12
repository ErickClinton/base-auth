package erick.clinton.baseauth.login;

import erick.clinton.baseauth.login.dto.LoginRequest;
import erick.clinton.baseauth.login.dto.LoginResponse;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtEncoder jwtEncoder;
    private final LoginService loginService;

    public LoginController(JwtEncoder jwtEncoder, LoginService loginService){
        this.jwtEncoder = jwtEncoder;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return this.loginService.login(loginRequest);
    }
}
