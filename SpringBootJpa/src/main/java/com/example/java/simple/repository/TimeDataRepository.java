package com.example.java.simple.repository;

import com.example.java.simple.domain.TimeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDataRepository extends JpaRepository<TimeData, Long> {
}
