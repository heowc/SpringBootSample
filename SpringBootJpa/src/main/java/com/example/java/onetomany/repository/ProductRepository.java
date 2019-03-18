package com.example.java.onetomany.repository;

import com.example.java.onetomany.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByName(String name);
}