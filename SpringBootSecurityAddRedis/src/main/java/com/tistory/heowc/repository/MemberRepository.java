package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
