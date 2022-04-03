package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data

@RequiredArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String message;

}
