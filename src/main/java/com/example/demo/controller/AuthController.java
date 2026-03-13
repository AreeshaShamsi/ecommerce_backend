package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        // Find user by email
        User existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if (existingUser == null) {
            return "User not found";
        }

        // Check password (hashed)
        if (!passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }
}
