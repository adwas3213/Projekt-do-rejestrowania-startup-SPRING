package com.example.registerstartupproject.Repository;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import org.springframework.data.repository.CrudRepository;

public interface RegisterTeamRepository extends CrudRepository<RegisterTeam,Long> {
    boolean existsByEmail(String email);
}
