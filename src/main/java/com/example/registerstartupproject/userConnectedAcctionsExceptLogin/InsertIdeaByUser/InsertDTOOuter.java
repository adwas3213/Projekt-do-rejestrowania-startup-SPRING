package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.InsertIdeaByUser;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class InsertDTOOuter {
    @NotNull
    @NotEmpty
    String email;
    @NotNull
    @NotEmpty
    String content;
}
