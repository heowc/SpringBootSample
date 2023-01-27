package com.heowc;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class EndOfDayJobConfig extends DefaultBatchConfiguration {

    @Override
    protected DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                                            .addScript("/org/springframework/batch/core/schema-h2.sql")
                                            .generateUniqueName(true)
                                            .build();
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return new JdbcTransactionManager(getDataSource());
    }

    @Bean
    public Job endOfDay(JobRepository jobRepository) {
        final JobExecutionDecider decider = (jobExecution, stepExecution) -> {
            final ExitStatus status = execute() ? ExitStatus.COMPLETED : ExitStatus.FAILED;
            return new FlowExecutionStatus(status.getExitCode());
        };

        return new JobBuilder("endOfDay", jobRepository)
                .start(step1(jobRepository))
                .next(decider).on(ExitStatus.COMPLETED.getExitCode()).to(step2(jobRepository)) // *, ?
                .from(decider).on(ExitStatus.FAILED.getExitCode()).stopAndRestart(step3(jobRepository)) // to(step3())
                .end()
                .build();
    }

    private static boolean execute() {
        return true;
    }

    @Bean
    public Step step1(JobRepository jobRepository) {
        return new StepBuilder("step1", jobRepository)
                .listener(new StepExecutionListener() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return ExitStatus.COMPLETED;
                    }
                })
                .tasklet((contribution, chunkContext) -> null, getTransactionManager())
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository) {
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> null, getTransactionManager())
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository) {
        return new StepBuilder("step3", jobRepository)
                .tasklet((contribution, chunkContext) -> null, getTransactionManager())
                .build();

    }
}
