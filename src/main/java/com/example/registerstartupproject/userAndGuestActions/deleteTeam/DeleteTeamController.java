package com.example.registerstartupproject.userAndGuestActions.deleteTeam;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"${frontEndLink}", "${localfrontURL}"}, allowedHeaders = "*")
public class DeleteTeamController {
    private final DeleteTeamService deleteTeamService;

    @DeleteMapping("/deleteTeam")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@CurrentSecurityContext SecurityContext user) {
        deleteTeamService.deleteAccount(user.getAuthentication().getName());
    }
}
