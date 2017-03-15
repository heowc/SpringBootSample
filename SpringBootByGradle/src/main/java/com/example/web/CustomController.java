package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomController {

	@Autowired
	private CustomerRepository repository;
	
	@PostMapping(value="/",
				produces="application/json")
	public Customer insert(@RequestBody Customer customer){
		return repository.save(customer);
	}
	
	@GetMapping(value="/{id}")
	public Customer findOne(@PathVariable Long id){
		return repository.findOne(id);
	}
}