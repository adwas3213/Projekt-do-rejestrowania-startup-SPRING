package com.example.registerstartupproject.repository;

import com.example.registerstartupproject.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
}
