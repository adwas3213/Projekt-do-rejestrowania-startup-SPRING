package com.example.registerstartupproject.Controllers.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressDTO {
    private String street;
    private int number;
    private String city;
    @Pattern(regexp = "^[0-9]{1,2}\\[0-9]{1,3}$")
    private String postalCode;
    private boolean isLeader;
    @NotNull
    @Email
    private String email;

}
