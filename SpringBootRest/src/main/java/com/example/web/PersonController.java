package com.example.web;

import com.example.domain.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestController
@RequestMapping("people")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("findByFirstName")
    public ResponseEntity<Resources<Person>> findByFirstName(@RequestParam(value = "firstName") String firstName) {
        Resources<Person> resources = new Resources<>(repository.findByFirstName(firstName));
        return ResponseEntity.ok(resources);
    }
}
