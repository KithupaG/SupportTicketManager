package com.spring.ems.controller;

import com.spring.ems.dto.UserResponse;
import com.spring.ems.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserResponse(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserResponse(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserResponseByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserResponseByEmail(email));
    }
}
