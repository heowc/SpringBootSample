package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    private MeterRegistry registry = new SimpleMeterRegistry();

    @GetMapping("/count")
    public Double count() {
        Counter counter = registry.counter("http.requests", "uri", "/simple/count");
        counter.increment();
        return counter.count();
    }
}