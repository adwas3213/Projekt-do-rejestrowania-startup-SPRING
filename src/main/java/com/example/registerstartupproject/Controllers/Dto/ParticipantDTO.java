package com.example.registerstartupproject.Controllers.Dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ParticipantDTO {
    private String name;
    private String surname;
    private String school;
    private BigInteger telephoneNumber;

}
