package com.spring.ems.dto;

import com.spring.ems.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Priority priority
) {
}
