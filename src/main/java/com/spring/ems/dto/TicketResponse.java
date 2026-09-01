package com.spring.ems.dto;

import com.spring.ems.model.Priority;
import com.spring.ems.model.Status;

import java.time.LocalDateTime;

public record TicketResponse(
    Long id,
    String title,
    String description,
    Priority priority,
    Status status,
    String customerName,
    String assignedAgentName,
    LocalDateTime createdAt
) {
}
