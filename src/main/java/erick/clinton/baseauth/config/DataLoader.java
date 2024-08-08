package erick.clinton.baseauth.config;

import erick.clinton.baseauth.role.entity.RoleEntity;
import erick.clinton.baseauth.role.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void run (String... args) throws Exception{
        Arrays.stream(RoleEntity.ValuesEnum.values())
                .forEach(role -> roleRepository.save(role.get()));
    }
}
