package com.example.registerstartupproject.userAndGuestActions.changePassword;

import com.example.registerstartupproject.userAndGuestActions.DTO.PasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"${frontEndLink}", "${localfrontURL}"}, allowedHeaders = "*")
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody PasswordDTO password
            , @CurrentSecurityContext SecurityContext user) {
        changePasswordService.changePassword(password.getPassword(), user.getAuthentication().getName());
    }
}
