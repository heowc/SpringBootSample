package com.example.java.simple.web;

import com.example.java.simple.domain.Customer;
import com.example.java.simple.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomController {

	@Autowired
	private CustomService service;

	@PostMapping
	public ResponseEntity<?> upsert(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.upsert(customer));
	}

	@GetMapping(value = "/findByName")
	public ResponseEntity<?> findByName(@RequestParam("name") String name) {
		return ResponseEntity.ok(service.findByName(name));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.find(id));
	}
}