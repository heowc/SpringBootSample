package com.example.java;

import com.example.java.service.BasicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
class SpringBootAsyncApplicationTests {

    @Autowired
    private BasicService service;

    @Test
    void test_async() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        service.onAsync();
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    @Test
    void test_sync() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        service.onSync();
        stopWatch.stop();
        System.out.println(stopWatch);
    }
}
