package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.EmailConnectedActions;

import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.ContactDTO;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.RegisterDtoOuter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "${frontEndLink}", allowedHeaders = "*")
public class RegisterController {
    private final RegisterService registerService;
    private final ContactService contactService;

    public RegisterController(RegisterService registerService, ContactService contactService) {
        this.registerService = registerService;
        this.contactService = contactService;
    }

    @PostMapping("/register")
    public ResponseEntity<StatusOfRequest> register(@RequestBody @Valid RegisterDtoOuter registerDtoOuter,
                                                    HttpServletResponse response) {
        StatusOfRequest responseStatus = registerService.createNewTeamWithValidation(registerDtoOuter);

        if(responseStatus== StatusOfRequest.EMAIL_EXIST)
        {
            return ResponseEntity.status(409).body(responseStatus);
        } else if(responseStatus== StatusOfRequest.ERROR_WITH_SENDING_EMAIL)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseStatus);


    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> verifyEmail(@RequestParam String token) {
        TokenToRegistry tokenToRegistry=new TokenToRegistry(token);
        boolean response = registerService.validateEmail(tokenToRegistry);
        if(response==false)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resendEmail")
    @ResponseStatus(HttpStatus.OK)
    public void resendEmailToGivenAddress(@RequestParam String email) {
        registerService.resendEmail(email);
    }

    @PostMapping("/contact-form")
    @ResponseStatus(HttpStatus.OK)
    public void contactFormSolver(@Valid @RequestBody ContactDTO contactDTO) {
        contactService.sendContactMessage(contactDTO);
    }

}
