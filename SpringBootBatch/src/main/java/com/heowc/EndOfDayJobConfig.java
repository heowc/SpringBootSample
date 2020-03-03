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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndOfDayJobConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public EndOfDayJobConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job endOfDay(JobBuilderFactory jobBuilderFactory) {
        final JobExecutionDecider decider = (jobExecution, stepExecution) -> {
            final ExitStatus status = execute() ? ExitStatus.COMPLETED : ExitStatus.FAILED;
            return new FlowExecutionStatus(status.getExitCode());
        };

        return jobBuilderFactory.get("endOfDay")
                .start(step1())
                .next(decider).on(ExitStatus.COMPLETED.getExitCode()).to(step2()) // *, ?
                .from(decider).on(ExitStatus.FAILED.getExitCode()).stopAndRestart(step3()) // to(step3())
                .end()
                .build();
    }

    private static boolean execute() {
        return true;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
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
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
