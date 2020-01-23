package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@RestController
@RequestMapping("/global")
public class GlobalController {

    {
        Metrics.addRegistry(new SimpleMeterRegistry());
    }

    @GetMapping("/count")
    public Double count() {
        Counter counter = Metrics.counter("http.requests", "uri", "/global/count");
        counter.increment();
        return counter.count();
    }
}