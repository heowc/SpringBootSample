package com.example.java.onetoone.repository;

import com.example.java.onetoone.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
