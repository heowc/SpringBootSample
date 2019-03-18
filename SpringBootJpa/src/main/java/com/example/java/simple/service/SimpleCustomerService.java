package com.example.java.simple.service;

import com.example.java.simple.domain.Customer;
import com.example.java.simple.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SimpleCustomerService implements CustomService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer upsert(Customer customer) {
		return repository.save(customer);
	}

	@Override
	public Customer find(Long idx) {
		return repository.findById(idx).orElseThrow(RuntimeException::new);
	}

	@Override
	public List<Customer> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Customer delete(Customer customer) {
		repository.delete(customer);
		return customer;
	}
}
