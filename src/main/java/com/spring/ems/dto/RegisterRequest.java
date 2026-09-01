package com.spring.ems.dto;

import com.spring.ems.model.AccessLevel;
import com.spring.ems.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull Role role,

        String phone,
        String address,
        String department,
        AccessLevel accessLevel,
        Integer maxActiveTickets
) {
}
