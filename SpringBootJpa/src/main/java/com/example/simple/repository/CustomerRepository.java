package com.example.simple.repository;

import com.example.simple.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomJpqlRepository {

}




