package com.example.simple.service;

import com.example.simple.domain.Customer;

import java.util.List;

public interface CustomService {

	Customer upsert(Customer customer);

	Customer find(Long idx);

	List<Customer> findByName(String name);

	Customer delete(Customer customer);
}
