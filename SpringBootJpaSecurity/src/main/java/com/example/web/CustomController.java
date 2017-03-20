package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;

@RestController
public class CustomController {

	@Autowired CustomerRepository repository;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	@GetMapping(value="/")
	public String index(){
		return "hello";
	}

	@GetMapping(value="/admin")
	public String indexAdmin(){
		return "hello admin";
	}
	
	@PostMapping(value="/insert",
				produces="application/json")
	public Customer insert(@RequestBody Customer customer){
		customer.setUserPassword(passwordEncoder.encode(customer.getUserPassword()));
		return repository.save(customer);
	}
	
	@GetMapping(value="/{id}")
	public Customer findOne(@PathVariable Long id){
		return repository.findOne(id);
	}
}