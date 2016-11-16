package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.component.BookRepository;
import com.example.domain.Book;

@RestController
public class BasicController {

	@Autowired
	BookRepository repository;
	
	@GetMapping("/")
	public Book getTest(@RequestParam("isbn") String isbn) {
		return repository.getByIsbn(isbn);
	}
	
	@GetMapping("/clear")
	public String refresh(@RequestParam("isbn") String isbn) {
		repository.refresh(isbn);
		return "Clear";
	}
}
