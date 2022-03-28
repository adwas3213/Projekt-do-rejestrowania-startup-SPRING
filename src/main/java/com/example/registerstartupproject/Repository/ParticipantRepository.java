package com.example.registerstartupproject.Repository;

import com.example.registerstartupproject.Repository.Entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Member,Long> {
}
