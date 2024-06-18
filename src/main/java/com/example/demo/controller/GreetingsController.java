package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/home")
    public String home() {
        return "<h1>Hello, Welcome to home<h1>";
    }

    @GetMapping("/users")
    public String hello() {
        return "<h1>Hello user, how are you?<h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Welcome to Admin<h1>";
    }
}
