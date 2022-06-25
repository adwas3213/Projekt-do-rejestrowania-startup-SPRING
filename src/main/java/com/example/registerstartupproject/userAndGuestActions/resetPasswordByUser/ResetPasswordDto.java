package com.example.registerstartupproject.userAndGuestActions.resetPasswordByUser;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String token;
    private String password;

}
