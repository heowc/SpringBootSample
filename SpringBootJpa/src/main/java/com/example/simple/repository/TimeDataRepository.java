package com.example.simple.repository;

import com.example.simple.domain.TimeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDataRepository extends JpaRepository<TimeData, Long> {
}
