package com.example.kotlin

import com.example.kotlin.component.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager

@SpringBootTest
class SpringBootCacheApplicationTests {

    @Autowired
    private lateinit var repository: BookRepository
    @Autowired
    private lateinit var cacheManager: CacheManager

    @Test
    fun `book 캐시에 a에 해당하는 값이 없다`() {
        cacheManager.getCache("book")?.let {
            assertThat(it.get("a")).isNull()
        }
    }

    @Test
    fun `b를 저장하면 book 캐시에 a에 해당하는 값이 있다`() {
        cacheManager.getCache("book")?.let {
            repository.getByIsbn("b")
            assertThat(it.get("b")).isNotNull
        }
    }

    @Test
    fun `c를 저장하면 book 캐시에 값이 있지만 refresh하면 값이 없다`() {
        cacheManager.getCache("book")?.let {
            repository.getByIsbn("c")
            assertThat(it.get("c")).isNotNull
            repository.refresh("c")
            assertThat(it.get("c")).isNull()
        }
    }
}
