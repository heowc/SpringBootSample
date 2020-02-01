package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/executor")
public class ExecutorServiceController {

    private final MeterRegistry registry = new SimpleMeterRegistry();
    private ExecutorService executorService;

    @GetMapping("/task")
    public void doTask() {
        executorService.execute(() -> {
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

    }

    @GetMapping("/metrics")
    public Map<String, Double> showMetrics() {
        final Map<String, Double> metricMap = new HashMap<>();
        metricMap.put("executor.active", registry.get("executor.active").gauge().value());
        metricMap.put("executor.pool.size", registry.get("executor.pool.size").gauge().value());
        metricMap.put("executor.queued", registry.get("executor.queued").gauge().value());
        metricMap.put("executor.completed", registry.get("executor.completed").functionCounter().count());
        metricMap.put("executor.queue.remaining", registry.get("executor.queue.remaining").gauge().value());
        return metricMap;
    }

    // https://micrometer.io/docs/ref/jvm
    @PostConstruct
    public void init() {
        executorService = Executors.newCachedThreadPool();
        new ExecutorServiceMetrics(executorService, "executor", Tags.of("id", "example")).bindTo(registry);
    }

    @PreDestroy
    public void destroy() {
        if (executorService != null) {
            try {
                executorService.shutdownNow();
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
