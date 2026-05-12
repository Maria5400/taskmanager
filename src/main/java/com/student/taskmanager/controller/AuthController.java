package com.student.taskmanager.controller;

import com.student.taskmanager.model.User;
import com.student.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // This creates the POST /auth/register endpoint!
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Note: We are leaving out POST /auth/login for this exact moment.
    // Spring Security has a built-in login system we are going to use in the next step!
}