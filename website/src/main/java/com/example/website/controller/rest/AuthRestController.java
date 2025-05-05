package com.example.website.controller.rest;

import com.example.website.entity.User;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        var result = userService.authenticate(username, password);
        Map<String, Object> response = new HashMap<>();

        if (result.isPresent()) {
            response.put("success", true);
            response.put("role", result.get().getRole());
        } else {
            response.put("success", false);
            response.put("error", "Неверный логин или пароль");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String password = userData.get("password");

        Map<String, Object> response = new HashMap<>();

        if (userService.registerUser(username, password)) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("error", "Пользователь с таким логином уже существует");
        }

        return ResponseEntity.ok(response);
    }
}