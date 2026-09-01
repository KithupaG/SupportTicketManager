package com.spring.ems.repository;

import com.spring.ems.model.Status;
import com.spring.ems.model.SupportAgent;
import com.spring.ems.model.Ticket;
import com.spring.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer(User customer);
    List<Ticket> findByAssignedAgent(SupportAgent agent);
    List<Ticket> findByStatusOrderByCreatedAtAsc(Status status);
}
