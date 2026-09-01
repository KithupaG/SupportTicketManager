package com.spring.ems.dto;

public record AuthResponse(
        String token,
        UserResponse user
) {
}
