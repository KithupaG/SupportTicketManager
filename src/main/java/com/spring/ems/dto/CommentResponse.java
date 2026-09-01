package com.spring.ems.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        String authorName,
        Long ticketId,
        LocalDateTime createdAt
) {
}
