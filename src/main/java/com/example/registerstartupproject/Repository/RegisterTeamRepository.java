package com.example.registerstartupproject.Repository;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegisterTeamRepository extends CrudRepository<RegisterTeam,Long> {
    boolean existsByEmail(String email);
    Optional<RegisterTeam> findByEmail(String email);
    Optional<RegisterTeam> getRegisterTeamsByTokenToRegistry(TokenToRegistry token);
}
