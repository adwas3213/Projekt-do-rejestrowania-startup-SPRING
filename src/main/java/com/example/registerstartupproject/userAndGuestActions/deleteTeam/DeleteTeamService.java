package com.example.registerstartupproject.userAndGuestActions.deleteTeam;

import com.example.registerstartupproject.repository.RegisterTeamRepository;
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
