package com.spring.ems.controller;

import com.spring.ems.dto.CommentRequest;
import com.spring.ems.dto.CommentResponse;
import com.spring.ems.service.CommentService;
import com.spring.ems.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class TicketCommentController {
    private final CommentService commentService;

    public TicketCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{ticketId}/{authorId}/comment")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long ticketId, @PathVariable Long authorId, @RequestBody CommentRequest request) {
        CommentResponse comment = commentService.addComment(ticketId, authorId, request);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<CommentResponse>> getCommentsForTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commentService.getCommentsForTicket(ticketId));
    }
}
