package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Office;
import com.example.domain.OfficeRepository;

@RestController
public class OfficeController {

	@Autowired
	OfficeRepository repository;
	
	@GetMapping(value = "/findOffice")
	public List<Office> findOffice(@RequestParam(value="name") String [] names){
		return repository.findByNameIn(names);
	}
}
