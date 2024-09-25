package ru.vovk.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vovk.springboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    void saveUser(User user);

    void updateUser(Long id, String username, String email, String password);

    void deleteUser(Long id);
}
