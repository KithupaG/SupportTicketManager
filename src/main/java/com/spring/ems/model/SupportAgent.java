package com.spring.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SupportAgent extends User {
    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private int maxActiveTickets;

    @Column(nullable = false)
    private int currentActiveTickets = 0;

    protected SupportAgent() {}

    public SupportAgent(String email, String password, String firstName, String lastName, Role role, String department, int maxActiveTickets, int currentActiveTickets) {
        super(email, password, firstName, lastName, role);
        this.department = department;
        this.maxActiveTickets = maxActiveTickets;
        this.currentActiveTickets = currentActiveTickets;
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

    public int getCurrentActiveTickets() {
        return currentActiveTickets;
    }

    public void getCurrentActiveTickets(int currentActiveTickets) {
        this.currentActiveTickets = currentActiveTickets;
    }
}
