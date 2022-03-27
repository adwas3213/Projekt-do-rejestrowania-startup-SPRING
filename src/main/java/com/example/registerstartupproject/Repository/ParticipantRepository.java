package com.example.registerstartupproject.Repository;

import com.example.registerstartupproject.Repository.Entity.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant,Long> {
}
