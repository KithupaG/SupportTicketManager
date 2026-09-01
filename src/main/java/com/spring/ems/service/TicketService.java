package com.spring.ems.service;

import com.spring.ems.dto.TicketRequest;
import com.spring.ems.dto.TicketResponse;
import com.spring.ems.model.*;
import com.spring.ems.repository.TicketRepository;
import com.spring.ems.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public TicketResponse createTicket(Long customerId, TicketRequest request) {
        User user = userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = new Ticket(
                request.title(),
                request.description(),
                Status.OPEN,
                request.priority()
        );
        ticket.setCustomer(user);
        ticketRepository.save(ticket);

        return toResponse(ticket);
    }

    public TicketResponse assignTicketToAgent(Long ticketId, Long agentId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User user = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Support agent not found"));

        if(user.getRole() != Role.SUPPORTER_AGENT) {
            throw new RuntimeException("User is not a support agent");
        }

        SupportAgent supportAgent = (SupportAgent) user;

        if(supportAgent.getCurrentActiveTickets() >= supportAgent.getMaxActiveTickets()) {
            throw new RuntimeException("Agent at max capacity");
        }

        ticket.setAssignedAgent(supportAgent);
        ticket.setStatus(Status.IN_PROGRESS);
        supportAgent.setCurrentActiveTickets(supportAgent.getCurrentActiveTickets() + 1);

        ticketRepository.save(ticket);
        userRepository.save(supportAgent);

        return toResponse(ticket);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TicketResponse updateTicketStatus(Long ticketId, Status newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if(ticket.getStatus().equals(Status.CLOSED)){
            throw new RuntimeException("Cannot change the state of a closed ticket");
        }
        ticket.setStatus(newStatus);

        return toResponse(ticket);
    }

    private TicketResponse toResponse(Ticket ticket) {
        String customerName =  ticket.getCustomer() != null ? ticket.getCustomer().getFirstName() : null;
        String agentName = ticket.getAssignedAgent() != null ? ticket.getAssignedAgent().getFirstName() : null;
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getStatus(),
                customerName,
                agentName,
                ticket.getCreatedAt()
        );
    }
}
