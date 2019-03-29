package com.example.kotlin.component

import com.example.kotlin.domain.Book
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class SimpleBookRepository : BookRepository {

    companion object {
        private val logger = LoggerFactory.getLogger(SimpleBookRepository::class.java)
        private const val CACHE_BOOK = "book"
    }

    @Cacheable(value = [CACHE_BOOK], key = "#isbn")
    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()
        return Book(isbn, "Some book")
    }

    private fun simulateSlowService() {
        try {
            Thread.sleep(3000L)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }

    }

    @CacheEvict(value = [CACHE_BOOK], key = "#isbn")
    override fun refresh(isbn: String) {
        logger.info("cache clear => $isbn")
    }
}

