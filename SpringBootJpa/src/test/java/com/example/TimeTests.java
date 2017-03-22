package com.example;

import com.example.simple.domain.TimeData;
import com.example.simple.repository.TimeDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTests {

    @Autowired TimeDataRepository timeDataRepository;

    @Test
    public void test_save() {
        timeDataRepository.save(new TimeData(LocalDateTime.now()));
    }

}
