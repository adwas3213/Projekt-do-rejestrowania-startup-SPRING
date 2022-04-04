package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDtoOuter {
    @NotEmpty
    @NotNull
    String street;
    @NotEmpty
    @NotNull
    String number;
    @NotEmpty
    @NotNull
    String postal;
    @NotEmpty
    @NotNull
    String city;
}
