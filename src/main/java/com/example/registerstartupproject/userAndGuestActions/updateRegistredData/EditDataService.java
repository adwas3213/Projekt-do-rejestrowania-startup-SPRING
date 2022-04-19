package com.example.registerstartupproject.userAndGuestActions.updateRegistredData;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.userAndGuestActions.DTO.RegisterDtoOuter;
import com.example.registerstartupproject.userAndGuestActions.emailConnectedActions.RegisterMapper;
import com.example.registerstartupproject.userAndGuestActions.exceptions.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditDataService {
    final private RegisterTeamRepository registerTeamRepository;
    final private RegisterMapper registerMapper;

    @Transactional
    public void editUserDataByUser(RegisterDtoOuter registerDtoOuter) {
        Optional<RegisterTeam> teamOptional = registerTeamRepository.findByEmail(registerDtoOuter.getEmail());
        if (teamOptional.isPresent()) {
            registerMapper.mapToRegisterTeamRegisterDtoOuter(registerDtoOuter, teamOptional.get());
        } else throw new UserNotFound("Logged Team not found");
    }

}
