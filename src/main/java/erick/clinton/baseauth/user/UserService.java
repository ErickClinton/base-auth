package erick.clinton.baseauth.user;

import erick.clinton.baseauth.client.notification.SendEmailService;
import erick.clinton.baseauth.client.notification.entity.EmailEntity;
import erick.clinton.baseauth.role.entity.RoleEntity;
import erick.clinton.baseauth.role.repository.RoleRepository;
import erick.clinton.baseauth.user.dto.CreateUserDto;
import erick.clinton.baseauth.user.entity.UserEntity;
import erick.clinton.baseauth.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SendEmailService sendEmailService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SendEmailService sendEmailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendEmailService = sendEmailService;
    }

    public ResponseEntity<Void> register(CreateUserDto createUserDto) {

        userRepository.findByUsername(createUserDto.username())
                .ifPresent(user -> {
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
                });

        var basicRole = roleRepository.findByName(RoleEntity.ValuesEnum.BASIC.name());

        var user = new UserEntity();
        user.setUsername(createUserDto.username());
        user.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
        user.setRoles(Set.of(basicRole));
        userRepository.save(user);

        var email = new EmailEntity();
        email.setFrom("");
        email.setTo("");
        email.setSubject("");
        email.setText("");

        CompletableFuture.runAsync(()->this.sendEmailService.sendEmail(email));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<UserEntity>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
