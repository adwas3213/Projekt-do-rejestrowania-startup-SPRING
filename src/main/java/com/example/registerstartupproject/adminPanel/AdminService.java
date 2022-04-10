package com.example.registerstartupproject.adminPanel;

import com.example.registerstartupproject.Repository.Entity.RegisterIdea;
import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.Entity.Status;
import com.example.registerstartupproject.Repository.RegisterIdeaRepository;
import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
private final RegisterTeamRepository registerTeamRepository;
private final RegisterIdeaRepository registerIdeaRepository;

public List<RegisterTeam> registerTeams()
{
    return (List<RegisterTeam>) registerTeamRepository.findAll();
}

@Transactional
public void setQualificationStatus(Long id, IdeaFromAdminDTO status)
{
    Optional<RegisterTeam> teamOptional = registerTeamRepository.findById(id);
    if(teamOptional.isPresent())
    {
        RegisterTeam team = teamOptional.get();
        if(team.getIdea()==null)
        {
            RegisterIdea idea=new RegisterIdea();
            idea.setReview(status.getReview());
            idea.setStatus(status.getStatus());
            idea.setReviewSendingDate(LocalDateTime.now());
            registerIdeaRepository.save(idea);
            team.setIdea(idea);

        }else
        {
            RegisterIdea idea = team.getIdea();
            idea.setStatus(status.getStatus());
            idea.setReview(status.getReview());
            idea.setReviewSendingDate(LocalDateTime.now());

        }
    }
}

}
