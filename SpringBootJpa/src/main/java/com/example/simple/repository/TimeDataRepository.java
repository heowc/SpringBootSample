package com.example.simple.repository;

import com.example.simple.domain.TimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TimeDataRepository extends JpaRepository<TimeData, Long> {
}
