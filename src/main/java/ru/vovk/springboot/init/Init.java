package ru.vovk.springboot.init;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vovk.springboot.model.Role;
import ru.vovk.springboot.model.User;
import ru.vovk.springboot.repository.RoleRepository;
import ru.vovk.springboot.repository.UserRepository;

import static java.util.Collections.singleton;

@Component
@AllArgsConstructor
public class Init {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    @PostConstruct
    private void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User admin = new User(
                "admin",
                encoder.encode("admin123"),
                "admin@email.com",
                singleton(roleAdmin));
        User user1 = new User(
                "user1",
                encoder.encode("user001"),
                "user1@email.com",
                singleton(roleUser));
        User user2 = new User(
                "user2",
                encoder.encode("user002"),
                "user2@email.com",
                singleton(roleUser));
        User user3 = new User(
                "user3",
                encoder.encode("user003"),
                "user3@email.com",
                singleton(roleUser));

        userRepository.save(admin);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
