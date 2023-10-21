package com.heowc.domain;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, String> {

    Stream<Member> streamByCreatedAtAfter(LocalDateTime createdAt);
}
