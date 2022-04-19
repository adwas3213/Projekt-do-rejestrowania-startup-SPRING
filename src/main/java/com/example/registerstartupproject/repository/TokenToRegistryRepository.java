package com.example.registerstartupproject.repository;

import com.example.registerstartupproject.entity.TokenToRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenToRegistryRepository extends JpaRepository<TokenToRegistry,String> {
}
