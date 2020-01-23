package com.example.kotlin

import com.example.kotlin.simple.domain.TimeData
import com.example.kotlin.simple.repository.TimeDataRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class TimeTests {

    @Autowired
    lateinit var timeDataRepository: TimeDataRepository

    @Test
    fun test_save() {
        timeDataRepository.save(TimeData(date = LocalDateTime.now()))
    }

}
