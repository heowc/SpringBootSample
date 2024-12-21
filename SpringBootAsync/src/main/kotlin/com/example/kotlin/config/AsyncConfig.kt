package com.example.kotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurer {

    override fun getAsyncExecutor(): Executor {
        //		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //		executor.setCorePoolSize(2);
        //		executor.setMaxPoolSize(10);
        //		executor.setQueueCapacity(500);
        //		executor.setThreadNamePrefix("heowc-async-");
        //		executor.initialize();
        return Executors.newWorkStealingPool()
    }
}
