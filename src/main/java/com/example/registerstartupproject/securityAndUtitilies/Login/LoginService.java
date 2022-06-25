package com.example.registerstartupproject.securityAndUtitilies.Login;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final RegisterTeamRepository registerTeamRepository;

    public Optional<RegisterTeam> findByEmailTeam(String username) {
        Optional<RegisterTeam> optionalRegisterTeam = registerTeamRepository.findByEmail(username);
        return optionalRegisterTeam;

    }


}
