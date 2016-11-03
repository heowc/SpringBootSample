package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Person;
import com.example.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping(value="findAll")
	public ResponseEntity<Resources<Person>> findAll(){
		Resources<Person> resources = new Resources<Person>(personService.findAll());
		return ResponseEntity.ok(resources);
	}
	
	@GetMapping(value="findByFirstName")
	public ResponseEntity<Resources<Person>> findByFirstName(@RequestParam(value="firstname", required=true) String firstname){
		Resources<Person> resources = new Resources<Person>(personService.findByFirstName(firstname));
		return ResponseEntity.ok(resources);
	}
}
