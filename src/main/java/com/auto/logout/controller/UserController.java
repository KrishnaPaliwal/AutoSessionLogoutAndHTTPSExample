package com.auto.logout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.auto.logout.model.User;
import com.auto.logout.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        if (userService.registerUser(user.getUsername(), user.getPassword()) != null) {
            return "redirect:/login";
        } else {
            // Handle registration failure, username already exists
            return "redirect:/register?error";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
