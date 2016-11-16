package com.example.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.SpringBootCacheApplicationTests;
import com.example.domain.Book;

@Component
public class SimpleBookRepository implements BookRepository {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootCacheApplicationTests.class);
	
	@Override
	@Cacheable(value="book", key="#isbn")
	public Book getByIsbn(String isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	@CacheEvict(value="book", key="#isbn")
	public void refresh(String isbn) {
		logger.info("cache clear => " + isbn);
	}
}

