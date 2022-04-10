package com.example.registerstartupproject.Repository.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.PERSIST, CascadeType.DELETE})
    List<Member> members;

    String username;

    String email;
    @JsonIgnore
    String password;
    boolean isEnabled = false;

    @OneToOne(cascade = javax.persistence.CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "token")
    TokenToRegistry tokenToRegistry;


    @OneToOne(fetch = FetchType.EAGER)

    RegisterIdea idea;


    @ManyToMany(fetch = FetchType.EAGER)

    private Set<Role> roles;


}
