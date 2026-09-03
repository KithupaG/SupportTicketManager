package com.spring.ems.controller;

import com.spring.ems.dto.AuthResponse;
import com.spring.ems.dto.LoginRequest;
import com.spring.ems.dto.RegisterRequest;
import com.spring.ems.dto.UserResponse;
import com.spring.ems.service.AuthService;
import com.spring.ems.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getUserResponseByEmail(email));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
