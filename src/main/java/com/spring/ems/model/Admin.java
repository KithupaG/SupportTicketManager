package com.spring.ems.model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
    @Column(nullable = false)
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessLevel accessLevel;

    protected Admin() {}

    public Admin(String email, String password, String firstName, String lastName, Role role, String department, AccessLevel accessLevel) {
        super(email ,password, firstName, lastName, role);
        this.department = department;
        this.accessLevel = accessLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
