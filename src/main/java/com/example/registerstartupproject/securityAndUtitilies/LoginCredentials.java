package com.example.registerstartupproject.securityAndUtitilies;

import lombok.Data;

@Data
public class LoginCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}