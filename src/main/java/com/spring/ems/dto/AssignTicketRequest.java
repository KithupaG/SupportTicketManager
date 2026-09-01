package com.spring.ems.dto;

import com.spring.ems.model.Status;
import jakarta.validation.constraints.NotNull;

public record AssignTicketRequest(
        @NotNull Long agentId,
        @NotNull Status status
) {
}
