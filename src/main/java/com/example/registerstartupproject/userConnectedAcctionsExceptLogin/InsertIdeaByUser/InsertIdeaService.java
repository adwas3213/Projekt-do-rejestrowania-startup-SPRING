package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.InsertIdeaByUser;

import com.example.registerstartupproject.Repository.Entity.RegisterIdea;
import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.RegisterIdeaRepository;
import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsertIdeaService {
    private final RegisterIdeaRepository registerIdeaRepository;
    private final RegisterTeamRepository registerTeamRepository;

    @Transactional
    public void setContentByUser (InsertDTOInner insertDTOInner)
    {
        String email = insertDTOInner.getEmail();
        Optional<RegisterTeam> foundTeam = registerTeamRepository.findByEmail(email);
        if(foundTeam.isEmpty())
        {
            throw new RuntimeException("Team not found");
        } else
        {
            RegisterIdea registerIdea= new RegisterIdea(insertDTOInner.getContent(),insertDTOInner.getDateOfCreation());
            registerIdeaRepository.save(registerIdea);
            foundTeam.get().setIdea(registerIdea);
        }

    }

}
