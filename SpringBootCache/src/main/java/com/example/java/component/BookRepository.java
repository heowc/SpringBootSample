package com.example.java.component;

import com.example.java.domain.Book;

public interface BookRepository {

	Book getByIsbn(String isbn);

	void refresh(String isbn);

}