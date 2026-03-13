package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController

public class SimpleController {

    @GetMapping("/greet")
    public String greet() {
        return "Hello from Spring Boot 🚀";
    }

}