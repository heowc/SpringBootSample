package com.example;

import org.assertj.core.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@RestController
@RequestMapping("/composite/filter")
public class CompositeWithFilterController {

    private CompositeMeterRegistry registry;

    {
        registry = new CompositeMeterRegistry();
        registry.add(new SimpleMeterRegistry());
        registry.add(new LoggingMeterRegistry());
        registry.config().meterFilter(MeterFilter.ignoreTags("foo"));
        registry.config().meterFilter(MeterFilter.maximumAllowableMetrics(5));
    }

    // i.m.c.i.logging.LoggingMeterRegistry     : http.requests{uri=/composite/filter/count} throughput=0.216667/s
    @GetMapping("/count")
    public Double count() {
        Counter counter = registry.counter("http.requests", Tags.of(Tag.of("uri", "/composite/filter/count"), Tag.of("foo", "bar")));
        counter.increment();
        return counter.count();
    }

    // https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot-actuator-autoconfigure/src/test/java/org/springframework/boot/actuate/autoconfigure/metrics/web/client/RestTemplateMetricsConfigurationTests.java#L77...L95
    @GetMapping("/index/{index}")
    public Integer indexCount(@PathVariable String index) {
        Counter counter = registry.counter("http.requests.index", "index", index);
        counter.increment();
        return registry.get("http.requests.index").meters().size();
    }
    
}