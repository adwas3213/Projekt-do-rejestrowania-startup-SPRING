package com.example.registerstartupproject.Repository;

import com.example.registerstartupproject.Repository.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
