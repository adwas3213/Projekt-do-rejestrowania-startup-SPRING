package com.example.registerstartupproject.userAndGuestActions.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super(message);
    }
}
