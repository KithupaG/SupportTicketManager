package com.spring.ems.service;

import com.spring.ems.dto.CommentRequest;
import com.spring.ems.dto.CommentResponse;
import com.spring.ems.model.Ticket;
import com.spring.ems.model.TicketComment;
import com.spring.ems.model.User;
import com.spring.ems.repository.TicketCommentRepository;
import com.spring.ems.repository.TicketRepository;
import com.spring.ems.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final TicketCommentRepository ticketCommentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public CommentService(TicketCommentRepository ticketCommentRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketCommentRepository = ticketCommentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse addComment(Long ticketId, Long authorId, CommentRequest request) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket doesnt exist!"));
        User author = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("User doesnt exist!"));

        var ticketComment = new TicketComment(request.content(), ticket, author);
        ticketCommentRepository.save(ticketComment);

        return toResponse(ticketComment);
    }

    public List<CommentResponse> getCommentsForTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket doesnt exist!"));
        return ticketCommentRepository.findByTicketOrderByCreatedAtAsc(ticket)
                .stream()
                .map(this::toResponse).toList();
    }

    private CommentResponse toResponse(TicketComment ticketComment) {
        String authorName = ticketComment.getAuthor().getFirstName();

        return new CommentResponse(
                ticketComment.getId(),
                ticketComment.getContent(),
                authorName,
                ticketComment.getTicket().getId(),
                ticketComment.getCreatedAt()
        );
    }




}
