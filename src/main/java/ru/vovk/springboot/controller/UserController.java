package ru.vovk.springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vovk.springboot.model.User;
import ru.vovk.springboot.service.UserService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    private static final String REDIRECT = "redirect:/";
    private UserService userService;

    @GetMapping("/index")
    public String indexPage() {
        return "index";
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
        return REDIRECT;
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.getUserByUsername(name).orElseThrow();
        model.addAttribute("user", user);
        return "user";
    }
}
