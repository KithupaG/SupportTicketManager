package com.spring.ems.controller;

import com.spring.ems.dto.AssignTicketRequest;
import com.spring.ems.dto.StatusUpdateRequest;
import com.spring.ems.dto.TicketRequest;
import com.spring.ems.dto.TicketResponse;
import com.spring.ems.model.Ticket;
import com.spring.ems.repository.TicketRepository;
import com.spring.ems.service.TicketService;
import com.spring.ems.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;
    private final TicketRepository ticketRepository;

    public TicketController(TicketService ticketService, UserService userService, TicketRepository ticketRepository) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(Authentication authentication, @RequestBody TicketRequest request) {
        String email = authentication.getName();
        Long customerId = userService.getUserResponseByEmail(email).id();
        TicketResponse created = ticketService.createTicket(customerId, request);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/{ticketId}/assign")
    public ResponseEntity<TicketResponse> assignTicketToAgent(@PathVariable Long ticketId, @RequestBody AssignTicketRequest request) {
        TicketResponse assigned = ticketService.assignTicketToAgent(ticketId, request.agentId());
        return ResponseEntity.ok(assigned);
    }

    @PostMapping("/{ticketId}/status")
    public ResponseEntity<TicketResponse> updateTicketStatus(@PathVariable Long ticketId, @RequestBody StatusUpdateRequest request) {
        TicketResponse updated = ticketService.updateTicketStatus(ticketId, request.status());
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketById(Long ticketId) {
        return ResponseEntity.ok(ticketService.getAllTicketById(ticketId));
    }
}
