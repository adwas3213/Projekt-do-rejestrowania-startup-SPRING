package com.example.registerstartupproject.userAndGuestActions.resetPasswordByUser;

import com.example.registerstartupproject.entity.ResetPasswordToken;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Data
@Slf4j
public class ResetPasswordController {
    private final ResetPasswordService resetPasswordService;
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto)
    {
        if(!resetPasswordService.checkIfTokenExists(resetPasswordDto.getToken()))
        {
            return ResponseEntity.notFound().build();
        }else
        {
            resetPasswordService.changePassword(resetPasswordDto);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/generateTokenToResetPassword")
    public ResponseEntity<?> generateToken(@RequestBody String email)
    {
        log.info("Token generated for % ",email);
        if(resetPasswordService.checkIfAccountWithEmailIsPresent(email))
        {
            resetPasswordService.generateResetToken(email);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();

    }

}
