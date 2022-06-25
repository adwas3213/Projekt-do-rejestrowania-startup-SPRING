package com.example.registerstartupproject.userAndGuestActions.resetPasswordByUser;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.entity.ResetPasswordToken;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.repository.ResetPasswordTokenRepository;
import com.example.registerstartupproject.securityAndUtitilies.HashPasswordService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final RegisterTeamRepository registerTeamRepository;
    private final HashPasswordService hashPasswordService;
    public boolean checkIfTokenExists(String token)
    {
       return resetPasswordTokenRepository.existsById(token);
    }
    public boolean checkIfAccountWithEmailIsPresent(String email)
    {
        System.out.println(email);
        Optional<RegisterTeam> optional = registerTeamRepository.findByEmail(email);
        System.out.println(optional);
        if(optional.isPresent())
        {
            return true;
        } else return false;
    }
    @Transactional
    public void generateResetToken(String email)
    {
        String uuid;
        do {
           uuid = UUID.randomUUID().toString();
        } while (resetPasswordTokenRepository.findById(uuid).isPresent());
        RegisterTeam registerTeam = registerTeamRepository.findByEmail(email).get();
        ResetPasswordToken resetPasswordToken=new ResetPasswordToken(uuid, registerTeam);
        resetPasswordTokenRepository.save(resetPasswordToken);
///TODO send  email with token to customer
    }

    public boolean changePassword(ResetPasswordDto resetPasswordDto) {
        Optional<ResetPasswordToken> optionalResetPasswordToken = resetPasswordTokenRepository.findById(resetPasswordDto.getToken());
        if(optionalResetPasswordToken.isEmpty())
        {
            return false;
        }
        hashPasswordService.setPasswordEncodedPassword(optionalResetPasswordToken.get().getRegisterTeam(), resetPasswordDto.getPassword());
        resetPasswordTokenRepository.delete(optionalResetPasswordToken.get());
        return true;
    }
}
