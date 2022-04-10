package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.updateRegistredData;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.RegisterDtoOuter;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.EmailConnectedActions.RegisterMapper;
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
        } else throw new RuntimeException("NIE ZNALEZIONO DROZYNY DO ZAMIANY");
    }

}
