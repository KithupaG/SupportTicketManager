package com.spring.ems.dto;

import com.spring.ems.model.Role;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        Role role
) {
}
