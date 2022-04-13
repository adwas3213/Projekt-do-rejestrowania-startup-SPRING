package com.example.registerstartupproject.adminPanel;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "${frontEndLink}", allowedHeaders = "*")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/getTeams")
    public List<RegisterTeam> getAllTeams() {
        var registerTeamList = adminService.registerTeams()
                .stream()
                .filter(registerTeam -> !(registerTeam.getId() == 1 || registerTeam.getId() == 2))
                .collect(Collectors.toList());
        return registerTeamList;
    }

    @PostMapping("/admin/setQualifcationStatusAndComment/{id}")
    public void setQualificationStatus(@PathVariable Long id, @RequestBody IdeaFromAdminDTO status) {
        adminService.setQualificationStatus(id, status);
    }

    @PostMapping("/admin/annouce")
    public void announce(@RequestBody AnnoucmentDto annoucmentDto) {
        adminService.announceToEveryTeam(annoucmentDto);
    }

}
