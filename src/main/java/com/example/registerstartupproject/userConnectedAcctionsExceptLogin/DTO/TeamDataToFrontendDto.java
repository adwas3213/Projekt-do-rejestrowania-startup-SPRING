package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO;

import com.example.registerstartupproject.Repository.Entity.Member;
import com.example.registerstartupproject.Repository.Entity.RegisterIdea;
import com.example.registerstartupproject.Repository.Entity.Role;
import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TeamDataToFrontendDto {

    private Long id;


    private List<Member> members;

    private String username;

    private String email;
    private boolean isEnabled;
    private String password;

    private TokenToRegistry tokenToRegistry;


    private RegisterIdea idea;


    private Set<Role> roles;

}
