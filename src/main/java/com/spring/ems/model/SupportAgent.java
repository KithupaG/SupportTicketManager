package com.spring.ems.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SupportAgent extends User {
    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private int maxActiveTickets;

    @Column(nullable = false)
    private int currentActiveTickets = 0;

    @OneToMany(mappedBy = "assignedAgent")
    private List<Ticket> tickets;

    protected SupportAgent() {
    }

    public SupportAgent(String email, String password, String firstName, String lastName, Role role, String department, int maxActiveTickets) {
        super(email, password, firstName, lastName, role);
        this.department = department;
        this.maxActiveTickets = maxActiveTickets;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMaxActiveTickets() {
        return maxActiveTickets;
    }

    public void setMaxActiveTickets(int maxActiveTickets) {
        this.maxActiveTickets = maxActiveTickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public int getCurrentActiveTickets() {
        return currentActiveTickets;
    }

    public void setCurrentActiveTickets(int currentActiveTickets) {
        this.currentActiveTickets = currentActiveTickets;
    }
}