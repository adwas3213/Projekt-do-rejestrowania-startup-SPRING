package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.RegisterDtoOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "${frontEndLink}" , allowedHeaders = "*" )
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody @Valid RegisterDtoOuter registerDtoOuter,
                            HttpServletResponse response)
    {

        boolean responseStatus = registerService.createNewTeamWithValidation(registerDtoOuter);
        return (responseStatus);
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
