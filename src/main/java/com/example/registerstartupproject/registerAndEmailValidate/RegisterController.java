package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.RegisterDtoOuter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/validate")
    public ResponseEntity<Boolean> verifyEmail(@RequestParam TokenToRegistry token)
    {

        boolean response=registerService.validateEmail(token);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/resendEmail")
    @ResponseStatus(HttpStatus.OK)
    public void resendEmailToGivenAddress(@RequestParam String email)
    {
        registerService.resendEmail(email);

    }
}
