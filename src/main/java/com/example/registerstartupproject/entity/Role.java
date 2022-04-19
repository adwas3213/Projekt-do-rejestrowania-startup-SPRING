package com.example.registerstartupproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Role {

    @Id
    @JsonProperty
    private String role;
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