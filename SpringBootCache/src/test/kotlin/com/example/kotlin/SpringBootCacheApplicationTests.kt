package com.example.kotlin

import com.example.kotlin.component.BookRepository
import org.junit.jupiter.api.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric::class)
class SpringBootCacheApplicationTests {

    @Autowired
    private lateinit var repository: BookRepository

    companion object {
        private var startTime: Long = 0
        private val logger = LoggerFactory.getLogger(SpringBootCacheApplicationTests::class.java)
    }

    @BeforeEach
    fun `onBefore`() {
        startTime = System.currentTimeMillis()
    }

    @AfterEach
    fun `onAfter`() {
        logger.info("소요시간: {}ms", System.currentTimeMillis() - startTime)
    }

    @Test
    fun `test1`() {
        repository.getByIsbn("a")
    }

    @Test
    fun `test2`() {
        repository.getByIsbn("a")
    }

    @Test
    fun `test3`() {
        repository.getByIsbn("b")
    }

    @Test
    fun `test4`() {
        repository.getByIsbn("a")
    }

    @Test
    fun `test5`() {
        repository.refresh("a")
        repository.getByIsbn("a")
    }
}
