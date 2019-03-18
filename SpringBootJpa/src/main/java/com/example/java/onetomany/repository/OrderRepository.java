package com.example.java.onetomany.repository;

import com.example.java.onetomany.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
