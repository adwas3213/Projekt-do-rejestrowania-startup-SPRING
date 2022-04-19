package com.example.registerstartupproject.userAndGuestActions.getDataToFrontEnd;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.userAndGuestActions.DTO.TeamDataToFrontendDto;
import com.example.registerstartupproject.userAndGuestActions.exceptions.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserDataService {
    private final RegisterTeamRepository registerTeamRepository;


    public TeamDataToFrontendDto getTeamData(String email) {
        Optional<RegisterTeam> teamOptional = registerTeamRepository.findByEmail(email);
        if (teamOptional.isPresent()) {
            RegisterTeam team = teamOptional.get();
            TeamDataToFrontendDto result = new TeamDataToFrontendDto();
            result.setEnabled(team.isEnabled());
            result.setEmail(team.getEmail());
            result.setMembers(team.getMembers());
            result.setUsername(team.getUsername());
            result.setRoles(team.getRoles());
            result.setIdea(team.getIdea());
            result.setPassword(team.getPassword());
            result.setId(team.getId());
            return result;
        } else throw new UserNotFound("Logged Team not found");
    }
}
