package com.example.repository;

import com.example.domain.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    SimpleUser findByEmail(String email);
}
