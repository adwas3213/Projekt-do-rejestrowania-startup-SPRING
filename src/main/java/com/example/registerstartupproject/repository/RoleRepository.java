package com.example.registerstartupproject.repository;

import com.example.registerstartupproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
