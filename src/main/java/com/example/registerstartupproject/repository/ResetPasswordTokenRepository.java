package com.example.registerstartupproject.repository;

import com.example.registerstartupproject.entity.ResetPasswordToken;
import org.springframework.data.repository.CrudRepository;

public interface ResetPasswordTokenRepository extends CrudRepository<ResetPasswordToken,String> {
}
