package com.example.registerstartupproject.Repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    String name;
    @NotEmpty
    @NotNull
    String surname;
    @NotEmpty
    @NotNull
    String school;
    @NotEmpty
    @NotNull
    String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    Address adress;


    boolean isLeader;
    @Email
    @NotEmpty
    String email;

}
