package com.example.kotlin

import com.example.kotlin.component.BookRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringBootCacheApplicationTests {

    @Autowired
    private lateinit var repository: BookRepository

    private var startTime: Long = 0

    companion object {
        private val logger = LoggerFactory.getLogger(SpringBootCacheApplicationTests::class.java)
    }

    @Before
    fun onBefore() {
        startTime = System.currentTimeMillis()
    }

    @After
    fun onAfter() {
        logger.info("소요시간: {}ms", System.currentTimeMillis() - startTime)
    }

    @Test
    fun test1() {
        repository.getByIsbn("a")
    }

    @Test
    fun test2() {
        repository.getByIsbn("a")
    }

    @Test
    fun test3() {
        repository.getByIsbn("b")
    }

    @Test
    fun test4() {
        repository.getByIsbn("a")
    }

    @Test
    fun test5() {
        repository.refresh("a")
        repository.getByIsbn("a")
    }
}
