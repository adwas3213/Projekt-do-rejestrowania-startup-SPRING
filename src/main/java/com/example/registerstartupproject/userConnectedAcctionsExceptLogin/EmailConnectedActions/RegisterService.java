package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.EmailConnectedActions;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.Entity.Role;
import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import com.example.registerstartupproject.Repository.RoleRepository;
import com.example.registerstartupproject.Repository.TokenToRegistryRepository;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.RegisterDtoOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RegisterService {

    private final EmailService emailService;
    private final RegisterTeamRepository registerTeamRepository;
    private final TokenToRegistryRepository tokenToRegistryRepository;
    private final RegisterMapper registerMapper;
    private final String frontEndLink;
    private final RoleRepository roleRepository;

    public StatusOfRequest createNewTeamWithValidation(RegisterDtoOuter registerDtoOuter)//DTO
    {

        boolean isPossiblyToCreateAccount = !registerTeamRepository.existsByEmail(registerDtoOuter.getEmail());

        if (isPossiblyToCreateAccount) {
            UUID uuid = UUID.randomUUID();
            String uuidWithOutDashes = uuid.toString().replace("-", "");
            do {
                uuid = UUID.randomUUID();
                uuidWithOutDashes = uuid.toString().replace("-", "");
            } while (tokenToRegistryRepository.findById(uuidWithOutDashes).isPresent());
            TokenToRegistry tokenToRegistry = new TokenToRegistry(uuidWithOutDashes);
            RegisterTeam registerTeam = registerMapper.mapToNewRegisterTeamRegisterDtoOuter(registerDtoOuter);
            registerTeam.setTokenToRegistry(tokenToRegistry);

            Set<Role> roles = new HashSet<>();
            if (roleRepository.findById("USER").isEmpty()) {
                Role user = new Role("USER");
                roleRepository.save(user);
                roles.add(user);
            } else {

                Role user = new Role("USER");
                roles.add(user);
            }

            registerTeam.setRoles(roles);

            try {
                emailService.sendMail(registerDtoOuter, tokenToRegistry);
                registerTeamRepository.save(registerTeam);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return StatusOfRequest.ERROR_WITH_SENDING_EMAIL;
            }
        } else {
            return StatusOfRequest.EMAIL_EXIST;
        }
        return StatusOfRequest.OK;
    }

    @Transactional
    public boolean validateEmail(TokenToRegistry tokenToRegistry) {
        Optional<RegisterTeam> teamOptional = registerTeamRepository.getRegisterTeamsByTokenToRegistry(tokenToRegistry);
        if (teamOptional.isPresent()) {
            var team = teamOptional.get();
            team.setEnabled(true);
            return true;
        } else {
            return false;
        }
    }

    public void resendEmail(String email) {
        Optional<RegisterTeam> foundEmail = registerTeamRepository.findByEmail(email);
        if (foundEmail.isEmpty()) {
            throw new RuntimeException("Sth went wrong with email");
        } else {
            try {
                emailService.sendMail(foundEmail.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public RegisterService(EmailService emailService, RegisterTeamRepository registerTeamRepository,
                           TokenToRegistryRepository tokenToRegistryRepository, RegisterMapper registerMapper,
                           @Value("${frontEndLink}") String frontEndLink, RoleRepository roleRepository) {
        this.emailService = emailService;
        this.registerTeamRepository = registerTeamRepository;
        this.tokenToRegistryRepository = tokenToRegistryRepository;
        this.registerMapper = registerMapper;
        this.frontEndLink = frontEndLink;
        this.roleRepository = roleRepository;
    }

}
