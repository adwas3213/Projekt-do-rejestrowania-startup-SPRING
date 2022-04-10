package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.updateRegistredData;

import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.RegisterDtoOuter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${frontEndLink}", allowedHeaders = "*")
public class EditDataController {

    private final EditDataService editDataService;

    @PatchMapping("/editUserData")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editUserData(@RequestBody @Valid RegisterDtoOuter registerDtoOuter) {
        editDataService.editUserDataByUser(registerDtoOuter);
    }

}
