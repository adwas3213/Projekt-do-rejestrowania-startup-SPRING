package com.example.registerstartupproject.userAndGuestActions.changePassword;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.userAndGuestActions.exceptions.UserNotFound;
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
        } else throw new UserNotFound("Not found logged user");
    }
}
