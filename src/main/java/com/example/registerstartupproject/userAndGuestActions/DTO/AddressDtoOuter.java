package com.example.registerstartupproject.userAndGuestActions.DTO;

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
    private String street;
    @NotEmpty
    @NotNull
    private String number;
    @NotEmpty
    @NotNull
    private String postal;
    @NotEmpty
    @NotNull
    private String city;
}
