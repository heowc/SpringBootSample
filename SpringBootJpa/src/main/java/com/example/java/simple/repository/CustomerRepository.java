package com.example.java.simple.repository;

import com.example.java.simple.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomJpqlRepository {

}




