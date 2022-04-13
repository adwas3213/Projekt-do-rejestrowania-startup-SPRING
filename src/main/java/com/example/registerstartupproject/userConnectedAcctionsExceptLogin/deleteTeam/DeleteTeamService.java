package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.deleteTeam;

import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTeamService {
    private final RegisterTeamRepository registerTeamRepository;

    public void deleteAccount(String user) {
        registerTeamRepository.delete(registerTeamRepository.findByEmail(user).get());
    }
}
