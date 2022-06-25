package com.example.registerstartupproject.adminPanel;

import com.example.registerstartupproject.entity.Address;
import com.example.registerstartupproject.entity.Member;
import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.entity.Role;
import com.example.registerstartupproject.repository.RegisterTeamRepository;
import com.example.registerstartupproject.repository.RoleRepository;
import com.example.registerstartupproject.securityAndUtitilies.HashPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BeginUsersSeed {
   private final HashPasswordService hashPasswordService;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.email}")
    private String adminEmail;
    @Value("${testUser.password}")
    private String testUserPassword;
    @Value("${testUser.email}")
    private String testUserEmail;
    private final RoleRepository roleRepository;
    private final RegisterTeamRepository registerTeamRepository;
    public void createAccounts() {
        createAdminAccount();
        createTestUserAccount();
    }
    private void createAdminAccount() {
        if (!registerTeamRepository.existsByEmail(adminEmail)) {
            Role role = new Role("ADMIN");
            if (!roleRepository.existsById("ADMIN")) {
                roleRepository.save(role);
            }
            RegisterTeam registerTeam = new RegisterTeam();
            hashPasswordService.setPasswordEncodedPassword(registerTeam,adminPassword);
//            registerTeam.setPassword(adminPassword);
            registerTeam.setEnabled(true);
            registerTeam.setRoles(Set.of(role));
            registerTeam.setUsername("Admin");
            registerTeam.setEmail(adminEmail);
            registerTeamRepository.save(registerTeam);
        }
    }
    private void createTestUserAccount() {
        if (!registerTeamRepository.existsByEmail(testUserEmail)) {
            Role role = new Role("TEST");
            if (!roleRepository.existsById("TEST")) {
                roleRepository.save(role);
            }
            RegisterTeam registerTeam = new RegisterTeam();
            hashPasswordService.setPasswordEncodedPassword(registerTeam,testUserPassword);
//            registerTeam.setPassword(testUserPassword);
            registerTeam.setEnabled(true);
            registerTeam.setRoles(Set.of(role));
            registerTeam.setUsername("TestUser");
            registerTeam.setEmail(testUserEmail);
            Member backend = new Member();
            backend.setName("Adam");
            backend.setSurname("Wasylewicz");
            backend.setSchool("Akademia Górniczo-Hutnicza");
            backend.setPhoneNumber("555555555");
            backend.setEmail("mail@example.com");
            Address address = new Address();
            address.setStreet("przykładowa ulica");
            address.setNumber("4");
            address.setPostal("33-130");
            address.setCity("Kraków");
            backend.setAddress(address);
            Member frontend = new Member();
            frontend.setName("Przemysław");
            frontend.setSurname("Matraj");
            frontend.setSchool("Wyższa Szkoła Ekonomii i informatyki w Krakowie");
            frontend.setPhoneNumber("444444444");
            frontend.setEmail("mail@example.com");
            frontend.setLeader(true);
            Address frontendAddress = new Address();
            frontendAddress.setCity("Tarnów");
            frontendAddress.setPostal("33-100");
            frontendAddress.setNumber("15");
            frontendAddress.setStreet("przykładowa ulica");
            frontend.setAddress(frontendAddress);
            registerTeam.setMembers(List.of(frontend, backend));
            registerTeamRepository.save(registerTeam);
        }
    }
}
