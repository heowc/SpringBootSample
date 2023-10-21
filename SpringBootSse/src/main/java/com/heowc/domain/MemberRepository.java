package com.heowc.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface MemberRepository extends JpaRepository<Member, String> {

    Stream<Member> findAllByCreatedAtAfter(LocalDateTime createdAt);
}
