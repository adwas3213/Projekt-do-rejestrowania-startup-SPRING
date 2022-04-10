package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.changePassword;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {
    private final RegisterTeamRepository registerTeamRepository;

    @Transactional
    public void changePassword(String password, String email) {
        Optional<RegisterTeam> teamOptional = registerTeamRepository.findByEmail(email);
        if (teamOptional.isPresent()) {
            RegisterTeam team = teamOptional.get();
            team.setPassword(password);
        } else throw new RuntimeException("Not found logged user");
    }
}
