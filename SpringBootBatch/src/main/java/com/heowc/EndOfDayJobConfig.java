package com.heowc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndOfDayJobConfig {

    private static final Logger logger = LoggerFactory.getLogger(EndOfDayJobConfig.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job endOfDay() {
        return this.jobBuilderFactory.get("endOfDay")
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("before job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("after job");
                    }
                })
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        logger.info("before step1");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        logger.info("after step1");
                        return ExitStatus.COMPLETED;
                    }
                })
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }

    @Bean
    public Step step2() {
        return this.stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
