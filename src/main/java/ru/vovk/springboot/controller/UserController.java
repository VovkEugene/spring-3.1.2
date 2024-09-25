package ru.vovk.springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vovk.springboot.model.User;
import ru.vovk.springboot.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private static final String REDIRECT = "redirect:/admin";
    private UserService userService;

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/create-form")
    public String createUserForm(User user) {
        return "create-form";
    }

    @PostMapping("/create-form")
    public String addUser(User user) {
        userService.saveUser(user);
        return REDIRECT;
    }

    @GetMapping("/edit-form")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow();
        model.addAttribute("user", user);
        return "edit-form";
    }

    @PostMapping("/edit-form")
    public String editUser(@RequestParam("id") Long id, User user) {
        userService.updateUser(id,
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        return REDIRECT;
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return REDIRECT;
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.getUserByUsername(name).orElseThrow();
        model.addAttribute("user", user);
        return "user";
    }
}
