package ru.vovk.springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vovk.springboot.model.Role;
import ru.vovk.springboot.model.User;
import ru.vovk.springboot.service.RoleService;
import ru.vovk.springboot.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private static final String REDIRECT = "redirect:/admin";
    private UserService userService;
    private RoleService roleService;

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
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "edit-form";
    }

    @PostMapping("/edit-form")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam(value = "isRemoveRoles", required = false) boolean isRemoveRoles,
                             User user) {
        userService.updateUser(id,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles(),
                isRemoveRoles);
        return REDIRECT;
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return REDIRECT;
    }
}
