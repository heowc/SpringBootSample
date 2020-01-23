package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@RestController
@RequestMapping("/composite")
public class CompositeController {

    private CompositeMeterRegistry registry;

    {
        registry = new CompositeMeterRegistry();
        registry.add(new SimpleMeterRegistry());
        registry.add(new LoggingMeterRegistry());
    }

    // i.m.c.i.logging.LoggingMeterRegistry     : http.requests{uri=/composite/count} throughput=0.216667/s
    @GetMapping("/count")
    public Double count() {
        Counter counter = registry.counter("http.requests", "uri", "/composite/count");
        counter.increment();
        return counter.count();
    }
}