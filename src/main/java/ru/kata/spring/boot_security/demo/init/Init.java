package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class Init {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void init() {
        roleRepository.save(new Role(1L, "ROLE_ADMIN"));
        roleRepository.save(new Role(2L, "ROLE_USER"));
        userService.save(new User("admin", "Vasek", "Chetkiy", 53, "VasyaChetkiy@gmail.com", "qwerty", roleRepository.findAll()));
        userService.save(new User("user", "Victor", "Gopnikov", 12, "Victor_na_raene@gmail.com", "bestpassword", roleRepository.findById(2L).stream().collect(Collectors.toList())));

    }
}
