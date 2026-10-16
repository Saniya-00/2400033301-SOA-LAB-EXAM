package com.soa.login.controller;

import com.soa.login.service.LoginAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginAuthController {

    private final LoginAuthService loginAuthService;

    public LoginAuthController(
            LoginAuthService loginAuthService) {
        this.loginAuthService = loginAuthService;
    }

    @PostMapping("/login-db")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body(
                Map.of("message",
                    "Username and password are required")
            );
        }

        String result = loginAuthService.authenticate(
                username, password);

        if (result.equals("Authentication Successful")) {
            return ResponseEntity.ok(Map.of("message", result));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", result));
    }
}