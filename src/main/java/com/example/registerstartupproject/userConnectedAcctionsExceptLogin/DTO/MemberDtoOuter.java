package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDtoOuter {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String surname;
    @Email
    @NotEmpty
    @NotNull
    private String email;
    @NotEmpty
    @NotNull
    private String school;
    @NotEmpty
    @NotNull
    private String phoneNumber;
    @NotEmpty
    @NotNull
    AddressDtoOuter address;

    Boolean isLeader;
}
