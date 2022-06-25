package com.example.registerstartupproject.userAndGuestActions.InsertIdeaFile;

import com.example.registerstartupproject.entity.RegisterIdea;
import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.repository.RegisterIdeaRepository;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.userAndGuestActions.exceptions.UserNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class InsertIdeaService {
    private final RegisterTeamRepository registerTeamRepository;

    private final String urlToHostedFiles;
    private final RegisterIdeaRepository registerIdeaRepository;
    public InsertIdeaService(RegisterTeamRepository registerTeamRepository,
                             @Value("${urlToHostedFiles}") String urlToHostedFiles, RegisterIdeaRepository registerIdeaRepository) {
        this.registerTeamRepository = registerTeamRepository;
        this.urlToHostedFiles = urlToHostedFiles;
        this.registerIdeaRepository = registerIdeaRepository;
    }

    @Transactional
    public void insertFilesWithIdea(InsertIdeaDTO ideaDTO) throws IOException {
        Optional<RegisterTeam> registerTeamOptional = registerTeamRepository
                .findByEmail(ideaDTO.getUser().getAuthentication().getName());
        if (registerTeamOptional.isPresent()) {
            RegisterTeam registerTeam = registerTeamOptional.get();
            String directoryPath = urlToHostedFiles + registerTeam.getId().toString();
            File folder = new File(directoryPath);
            if (!folder.isDirectory()) {
                folder.mkdir();
                log.info("Directory " + folder.getName() + " Created");
            }


            String fileNameIdea = ideaDTO.getIdea().getOriginalFilename();
            String fileNameApplication = ideaDTO.getApplication().getOriginalFilename();
            var partsOfNameIdeaSplitedByDots = fileNameIdea.split("\\.");
            var partsOfNameApplicationSplitedByDots = fileNameApplication.split("\\.");

            String extensionForIdea = partsOfNameIdeaSplitedByDots[partsOfNameIdeaSplitedByDots.length - 1];
            String extensionForApplication = partsOfNameApplicationSplitedByDots[partsOfNameApplicationSplitedByDots.length - 1];
            String ideaPath = directoryPath + "\\" + registerTeam.getUsername() + "_pomysl." + extensionForIdea;
            ideaDTO.getIdea().transferTo(new File(ideaPath));
            log.info("File " + fileNameIdea + " saved as " + ideaPath);

            String applicationPath = directoryPath + "\\" + registerTeam.getUsername() + "_aplikacja." + extensionForApplication;
            ideaDTO.getApplication().transferTo(new File(applicationPath));
            log.info("File " + fileNameApplication + " saved as " + applicationPath);
            if (registerTeam.getIdea() == null) {
                RegisterIdea registerIdea=new RegisterIdea();
                registerIdeaRepository.save(registerIdea);
                registerTeam.setIdea(registerIdea);
            }
            if (!ideaDTO.getIdea().isEmpty())
                registerTeam.getIdea().setIdeaLink(ideaPath);
            if (!ideaDTO.getApplication().isEmpty())
                registerTeam.getIdea().setApplicationLink(applicationPath);
            if (!ideaDTO.getIdea().isEmpty() || ideaDTO.getApplication().isEmpty())
                registerTeam.getIdea().setInsertIdeaAndApplicationTime(LocalDateTime.now());

        } else throw new UserNotFound("USER NOT FOUND");

    }

}
