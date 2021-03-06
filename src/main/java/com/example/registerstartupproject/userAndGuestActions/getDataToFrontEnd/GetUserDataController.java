package com.example.registerstartupproject.userAndGuestActions.getDataToFrontEnd;

import com.example.registerstartupproject.userAndGuestActions.DTO.TeamDataToFrontendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"${frontEndLink}", "${localfrontURL}"}, allowedHeaders = "*")
public class GetUserDataController {
    private final GetUserDataService getUserDataService;

    @GetMapping("/getUserData")
    public TeamDataToFrontendDto getDataFromBackend(@CurrentSecurityContext SecurityContext user) {
        return getUserDataService.getTeamData(user.getAuthentication().getName());
    }
}
