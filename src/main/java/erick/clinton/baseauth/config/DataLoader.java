package erick.clinton.baseauth.config;

import erick.clinton.baseauth.role.entity.RoleEntity;
import erick.clinton.baseauth.role.repository.RoleRepository;
import erick.clinton.baseauth.user.entity.UserEntity;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Set;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run (String... args){
        Arrays.stream(RoleEntity.ValuesEnum.values())
                .forEach(role -> roleRepository.save(role.get()));

        var roleAdmin = roleRepository.findByName(RoleEntity.ValuesEnum.ADMIN.name());
        var userAdmin  = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                (user)-> {
                    System.out.println("User already exist");
                },
                ()->{

                        var user = new UserEntity();
                        user.setUsername("admin");
                        user.setPassword(bCryptPasswordEncoder.encode("123"));
                        user.setRoles(Set.of(roleAdmin));
                        userRepository.save(user);
                });
    }
}
