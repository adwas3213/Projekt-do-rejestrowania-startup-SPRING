package com.example.registerstartupproject.Repository.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Role {
    @Id
    String role;

    @Override
    public String toString() {
        return role;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }
}