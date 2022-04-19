package com.example.registerstartupproject.adminPanel;

import com.example.registerstartupproject.entity.RegisterIdea;
import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.repository.RegisterIdeaRepository;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.userAndGuestActions.emailConnectedActions.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final RegisterTeamRepository registerTeamRepository;
    private final RegisterIdeaRepository registerIdeaRepository;
    private final EmailService emailService;

    public List<RegisterTeam> registerTeams() {
        return (List<RegisterTeam>) registerTeamRepository.findAll();
    }

    @Transactional
    public void setQualificationStatus(Long id, IdeaFromAdminDTO status) {
        Optional<RegisterTeam> teamOptional = registerTeamRepository.findById(id);
        if (teamOptional.isPresent()) {
            RegisterTeam team = teamOptional.get();
            if (team.getIdea() == null) {
                RegisterIdea idea = new RegisterIdea();
                idea.setReview(status.getReview());
                idea.setStatus(status.getStatus());
                idea.setReviewSendingDate(LocalDateTime.now());
                registerIdeaRepository.save(idea);
                team.setIdea(idea);
            } else {
                RegisterIdea idea = team.getIdea();
                idea.setStatus(status.getStatus());
                idea.setReview(status.getReview());
                idea.setReviewSendingDate(LocalDateTime.now());
            }
        }
    }
    public void announceToEveryTeam(AnnoucmentDto annoucmentDto) {
        List<RegisterTeam> teams = (List<RegisterTeam>) registerTeamRepository.findAll();
        List<RegisterTeam> correctTeams = teams.stream()
                .filter(registerTeam -> !(registerTeam.getId() == 1 || registerTeam.getId() == 2))
                .collect(Collectors.toList());
        for (RegisterTeam correctTeam : correctTeams) {
            try {
                emailService.sendMail(annoucmentDto, correctTeam.getEmail(), correctTeam);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
