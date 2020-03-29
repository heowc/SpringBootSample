package com.example.java;

import com.example.java.component.BookRepository;
import com.example.java.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootCacheApplicationTests {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void nullValue() {
        final Cache bookCache = cacheManager.getCache("book");
        assertThat(bookCache.get("a1", Book.class)).isNull();
    }

    @Test
    void notNullValue() {
        final Cache bookCache = cacheManager.getCache("book");
        repository.getByIsbn("b1");
        assertThat(bookCache.get("b1", Book.class)).isNotNull();
    }

    @Test
    void refreshAndNullValue() {
        final Cache bookCache = cacheManager.getCache("book");
        repository.getByIsbn("c1");
        assertThat(bookCache.get("c1", Book.class)).isNotNull();
        repository.refresh("c1");
        assertThat(bookCache.get("c1", Book.class)).isNull();
    }
}
