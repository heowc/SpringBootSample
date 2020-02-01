package com.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/composite/filter")
public class CompositeWithFilterController {

    private static final Logger logger = LoggerFactory.getLogger(CompositeWithFilterController.class);
    private final CompositeMeterRegistry registry;

    public static final String HTTP_EXAMPLE_REQUESTS = "http.example.requests";

    {
        registry = new CompositeMeterRegistry();
        registry.add(new SimpleMeterRegistry());
        registry.add(new LoggingMeterRegistry());
        registry.config()
                .meterFilter(MeterFilter.ignoreTags("foo"))
                .meterFilter(MeterFilter.maximumAllowableMetrics(5))
                .meterFilter(MeterFilter.maximumAllowableTags(HTTP_EXAMPLE_REQUESTS, "uri", 3, new MeterFilter() {
                    private final AtomicBoolean alreadyWarned = new AtomicBoolean(false);

                    @Override
                    @NonNull
                    public MeterFilterReply accept(@NonNull Meter.Id id) {
                        if (alreadyWarned.compareAndSet(false, true)) {
                            logger.warn("Reached the maximum number of URI tags for '" +
                                    HTTP_EXAMPLE_REQUESTS + "'. Are you using uriVariables on RestTemplate calls?");
                        }
                        return MeterFilterReply.DENY;
                    }
                }));
    }

    // i.m.c.i.logging.LoggingMeterRegistry     : http.requests{uri=/composite/filter/count} throughput=0.216667/s
    @GetMapping("/count")
    public Double count() {
        final Counter counter = registry.counter("http.requests", Tags.of(Tag.of("uri", "/composite/filter/count"), Tag.of("foo", "bar")));
        counter.increment();
        return counter.count();
    }

    // https://github.com/spring-projects/spring-boot/blob/01933f9/spring-boot-project/spring-boot-actuator-autoconfigure/src/test/java/org/springframework/boot/actuate/autoconfigure/metrics/web/client/RestTemplateMetricsConfigurationTests.java#L77...L95
    @GetMapping("/index/{index}")
    public Map<String, Integer> indexCount(@PathVariable String index) {
        final String metricName = "http.requests.index";
        final Counter indexCounter = registry.counter(metricName, "index", index);
        indexCounter.increment();

        final Counter requestCounter = registry.counter(HTTP_EXAMPLE_REQUESTS, "uri", "/index/" + index);
        requestCounter.increment();

        final Map<String, Integer> result = new HashMap<>();
        result.put(metricName, registry.get(metricName).meters().size());
        result.put(HTTP_EXAMPLE_REQUESTS, registry.get(HTTP_EXAMPLE_REQUESTS).meters().size());
        return result;
    }


}