package com.example.java.component;

import com.example.java.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

	private static final Logger logger = LoggerFactory.getLogger(SimpleBookRepository.class);

	private static final String CACHE_BOOK = "book";

	@Override
	@Cacheable(value = CACHE_BOOK, key = "#isbn")
	public Book getByIsbn(String isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	private void simulateSlowService() {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	@CacheEvict(value = CACHE_BOOK, key = "#isbn")
	public void refresh(String isbn) {
		logger.info("cache clear => " + isbn);
	}
}

