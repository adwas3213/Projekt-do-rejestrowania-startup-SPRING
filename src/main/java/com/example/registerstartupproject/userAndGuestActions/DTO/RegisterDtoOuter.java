package com.example.registerstartupproject.userAndGuestActions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtoOuter {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @Email
    @NotNull
    @NotEmpty
    private String email;
    @NotEmpty
    @NotNull
    private List<MemberDtoOuter> members;


}
