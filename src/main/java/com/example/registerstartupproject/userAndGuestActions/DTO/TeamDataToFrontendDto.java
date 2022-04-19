package com.example.registerstartupproject.userAndGuestActions.DTO;

import com.example.registerstartupproject.entity.Member;
import com.example.registerstartupproject.entity.RegisterIdea;
import com.example.registerstartupproject.entity.Role;
import com.example.registerstartupproject.entity.TokenToRegistry;
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
