package com.tistory.heowc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tistory.heowc.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
