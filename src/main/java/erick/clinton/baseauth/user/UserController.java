package erick.clinton.baseauth.user;

import erick.clinton.baseauth.user.dto.CreateUserDto;
import erick.clinton.baseauth.user.entity.UserEntity;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDto createUserDto){return this.userService.register(createUserDto);}

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserEntity>> getAll(){return this.userService.getAll();}
}
