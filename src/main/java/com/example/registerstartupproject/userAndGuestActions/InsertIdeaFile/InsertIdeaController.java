package com.example.registerstartupproject.userAndGuestActions.InsertIdeaFile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class InsertIdeaController {

    private final InsertIdeaService ideaService;


    @PostMapping("/insertIdea")
    public ResponseEntity<?> insertIdeaByUser(@RequestPart MultipartFile idea,
                                              @RequestPart MultipartFile application,
                                              @CurrentSecurityContext SecurityContext user) throws IOException {
        ideaService.insertFilesWithIdea(new InsertIdeaDTO(idea, application, user));
        return ResponseEntity.ok()
                .build();
    }
}
