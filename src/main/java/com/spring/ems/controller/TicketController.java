package com.spring.ems.controller;

import com.spring.ems.dto.AssignTicketRequest;
import com.spring.ems.dto.StatusUpdateRequest;
import com.spring.ems.dto.TicketRequest;
import com.spring.ems.dto.TicketResponse;
import com.spring.ems.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request) {
        Long customerId = 1L;
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
}
