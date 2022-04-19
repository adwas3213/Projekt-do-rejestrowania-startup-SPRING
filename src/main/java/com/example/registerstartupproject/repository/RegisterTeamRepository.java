package com.example.registerstartupproject.repository;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.entity.TokenToRegistry;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegisterTeamRepository extends CrudRepository<RegisterTeam,Long> {
    boolean existsByEmail(String email);
    Optional<RegisterTeam> findByEmail(String email);
    Optional<RegisterTeam> getRegisterTeamsByTokenToRegistry(TokenToRegistry token);
}
