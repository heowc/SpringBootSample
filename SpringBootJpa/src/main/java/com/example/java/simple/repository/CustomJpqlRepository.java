package com.example.java.simple.repository;

import com.example.java.simple.domain.Customer;

import java.util.List;

public interface CustomJpqlRepository {

	List<Customer> findByName(String name);
}
