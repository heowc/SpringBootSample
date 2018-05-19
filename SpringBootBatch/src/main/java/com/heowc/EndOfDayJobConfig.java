package com.heowc;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndOfDayJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job endOfDay() {
        JobExecutionDecider decider = (jobExecution, stepExecution) -> {
            ExitStatus status = execute() ? ExitStatus.COMPLETED : ExitStatus.FAILED;
            return new FlowExecutionStatus(status.getExitCode());
        };

        return this.jobBuilderFactory.get("endOfDay")
                .start(step1())
                .next(decider).on(ExitStatus.COMPLETED.getExitCode()).to(step2()) // *, ?
                .from(decider).on(ExitStatus.FAILED.getExitCode()).stopAndRestart(step3()) // to(step3())
                .end()
                .build();
    }

    private boolean execute() {
        return true;
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .listener(new StepExecutionListenerSupport() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
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

    @Bean
    public Step step3() {
        return this.stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
