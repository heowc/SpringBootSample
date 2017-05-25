package com.example.web;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomController {

	private final CustomerRepository repository;
	private final PasswordEncoder passwordEncoder;
	
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