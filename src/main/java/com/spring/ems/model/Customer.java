package com.spring.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {
    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String address;

    protected Customer() {}

    public Customer(String email, String password, String firstName, String lastName, Role role, String phone, String address) {
        super(email, password, firstName, lastName, role);
        this.phone = phone;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
