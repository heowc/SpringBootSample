package com.example.simple.repository;

import com.example.simple.domain.Customer;

import java.util.List;

public interface CustomJpqlRepository {

	List<Customer> findByName(String name);
}
