package com.example.registerstartupproject.securityAndUtitilies;

import com.example.registerstartupproject.entity.RegisterTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HashPasswordService {

   private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   @Transactional
    public void setPasswordEncodedPassword(RegisterTeam registerTeam,String password)
    {
        registerTeam.setPassword(passwordEncoder.encode(password));
    }
}
