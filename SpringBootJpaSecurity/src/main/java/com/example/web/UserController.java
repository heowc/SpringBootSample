package com.example.web;

import com.example.domain.User;
import com.example.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> findOne(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping("match/{id}")
    @PreAuthorize("#id == authentication.principal.username")
    public ResponseEntity<User> match(@PathVariable String id, Authentication authentication) {
        return ResponseEntity.ok().build();
    }
}