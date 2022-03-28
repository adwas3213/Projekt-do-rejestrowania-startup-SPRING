package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.registerAndEmailValidate.DTO.RegisterDtoOuter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterDtoOuter registerDtoOuter)//jakieś DTO
    {
        boolean response = registerService.createNewTeamWithValidation(registerDtoOuter);
        return ResponseEntity.ok(response);
    }


    ///TODO 1) zmapowanie /register
    //OK
    ///TODO 2) zmapowanie /validate
    ///TODO 3) zmapowanie /resendEmail
}
