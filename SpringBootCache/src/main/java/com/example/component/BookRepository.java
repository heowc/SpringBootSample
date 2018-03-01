package com.example.component;

import com.example.domain.Book;

public interface BookRepository {

	Book getByIsbn(String isbn);

	void refresh(String isbn);

}