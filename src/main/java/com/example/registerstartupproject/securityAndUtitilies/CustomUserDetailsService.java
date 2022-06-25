package com.example.registerstartupproject.securityAndUtitilies;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.entity.Role;
import com.example.registerstartupproject.securityAndUtitilies.Login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<RegisterTeam> teamOptional = loginService.findByEmailTeam(username);
        if (teamOptional.isPresent()) {

            RegisterTeam registerTeam = teamOptional.get();
            String[] roles;
            roles = registerTeam.getRoles().stream().map(Role::toString).toArray(String[]::new);
            return User
                    .builder()
                    .username(registerTeam.getEmail())
                    .password(registerTeam.getPassword())
                    .roles(roles)
                    .build();

        } else {
            return null;
        }


    }
}
