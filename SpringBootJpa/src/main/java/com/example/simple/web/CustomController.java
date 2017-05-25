package com.example.simple.web;

import com.example.simple.domain.Customer;
import com.example.simple.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomController {

	private final CustomService service;
	
	@PostMapping(value="/",
				produces="application/json")
	public Customer insert(@RequestBody Customer customer){
		return service.insert(customer);
	}
	
	@GetMapping(value="/{id}")
	public Customer find(@PathVariable Long id){
		return service.find(id);
	}
}