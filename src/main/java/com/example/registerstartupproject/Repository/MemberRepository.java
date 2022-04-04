package com.example.registerstartupproject.Repository;

import com.example.registerstartupproject.Repository.Entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
}
