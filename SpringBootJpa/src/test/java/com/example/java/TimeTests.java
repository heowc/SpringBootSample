package com.example.java;

import com.example.java.simple.domain.TimeData;
import com.example.java.simple.repository.TimeDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TimeTests {

    @Autowired TimeDataRepository timeDataRepository;

    @Test
    void test_save() {
        timeDataRepository.save(new TimeData(LocalDateTime.now()));
    }

}
