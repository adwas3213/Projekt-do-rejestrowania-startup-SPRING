package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.Repository.RegisterTeamRepository;
import com.example.registerstartupproject.Repository.TokenToRegistryRepository;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.RegisterDtoOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterService {

    private final EmailService emailService;
    private final RegisterTeamRepository registerTeamRepository;
    private final TokenToRegistryRepository tokenToRegistryRepository;
    private final RegisterMapper registerMapper;
    private final String frontEndLink;


    public boolean createNewTeamWithValidation(RegisterDtoOuter registerDtoOuter)//DTO
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
            RegisterTeam registerTeam= registerMapper.mapToRegisterTeamRegisterDtoOuter(registerDtoOuter);
            registerTeam.setTokenToRegistry(tokenToRegistry);
            registerTeamRepository.save(registerTeam);
            //OK
            try {
                System.out.println(registerDtoOuter);
                emailService.sendMail(registerDtoOuter,tokenToRegistry);

            } catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }


            // System.out.println(uuidWithOutDashes);

        } else {
            return false;
        }
//        System.out.println(registerDtoOuter);
        return true;
    }

    public RegisterService(EmailService emailService, RegisterTeamRepository registerTeamRepository,
                           TokenToRegistryRepository tokenToRegistryRepository, RegisterMapper registerMapper, @Value("${frontEndLink}") String frontEndLink) {
        this.emailService = emailService;
        this.registerTeamRepository = registerTeamRepository;
        this.tokenToRegistryRepository = tokenToRegistryRepository;
        this.registerMapper = registerMapper;
        this.frontEndLink = frontEndLink;
    }

}
