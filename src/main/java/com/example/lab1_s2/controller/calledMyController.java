package com.example.lab1_s2.controller;

import com.example.lab1_s2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class calledMyController {

    // 2. Declare private field + @Autowired
    @Autowired
    private UserService userService;

    // 3. GET /register → show registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // 4. POST /register → handle form submission
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password
    ) {

        // 4c. Call UserService to save user
        userService.registerUser(username, password);

        // 4d. Redirect to login page
        return "redirect:/login";
    }

    // 5. GET /login → show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }
}
