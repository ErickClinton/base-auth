package erick.clinton.baseauth.login;

import erick.clinton.baseauth.login.dto.LoginRequest;
import erick.clinton.baseauth.login.dto.LoginResponse;
import erick.clinton.baseauth.role.entity.RoleEntity;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        final var user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new BadCredentialsException("User not exist" + loginRequest.username()));

        if(!user.isLogginCorrect(loginRequest,bCryptPasswordEncoder)){
            throw new BadCredentialsException("User not exist" + loginRequest.username());
        }

        var now = Instant.now();
        var expiresIn = 300L;
        var scope = user.getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("api")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope",scope)
                .build();


        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

    }
}
