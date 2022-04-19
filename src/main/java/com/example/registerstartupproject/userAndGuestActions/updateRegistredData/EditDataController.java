package com.example.registerstartupproject.userAndGuestActions.updateRegistredData;

import com.example.registerstartupproject.userAndGuestActions.DTO.RegisterDtoOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"${frontEndLink}", "${localfrontURL}"}, allowedHeaders = "*")
public class EditDataController {

    private final String testUsername;

    private final EditDataService editDataService;

    public EditDataController(@Value("${testUser.email}") String testUsername, EditDataService editDataService) {
        this.testUsername = testUsername;
        this.editDataService = editDataService;
    }

    @PatchMapping("/editUserData")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editUserData(@RequestBody @Valid RegisterDtoOuter registerDtoOuter
            , @CurrentSecurityContext SecurityContext context) {
        if (!context.getAuthentication().getName().equals(testUsername))
            editDataService.editUserDataByUser(registerDtoOuter);
    }

}
