package com.heowc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringBootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBatchApplication.class, args);
    }

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public Job footballJob() {
//        return this.jobBuilderFactory.get("footballJob")
//                .preventRestart()
//                .start(playerLoad())
//                .next(gameLoad())
//                .next(playerSummarization())
//                .end()
//                .build();
//    }
}

@Configuration
@EnableBatchProcessing
class BatchConfig {
}
