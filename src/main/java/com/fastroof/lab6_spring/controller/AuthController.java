package com.fastroof.lab6_spring.controller;

import com.fastroof.lab6_spring.pojo.UserRegistrationRequest;
import com.fastroof.lab6_spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "thymeleaf/login";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(ModelMap model) {
        model.addAttribute("user", new UserRegistrationRequest());
        return "thymeleaf/registration";
    }

    @PostMapping("/registration")
    public String processRegister(ModelMap model, UserRegistrationRequest request) {
        model.addAllAttributes(authService.processRegister(request));
        return "info";
    }
}