package com.example.registerstartupproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordToken {
    @Id
    private String id;
    @ManyToOne
    RegisterTeam registerTeam;
}
