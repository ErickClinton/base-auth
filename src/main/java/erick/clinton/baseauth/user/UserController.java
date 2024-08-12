package erick.clinton.baseauth.user;

import erick.clinton.baseauth.user.dto.CreateUserDto;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDto createUserDto){

        return this.userService.register(createUserDto);

    }
}
