package com.example.java.onetoone.repository;

import com.example.java.onetoone.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
