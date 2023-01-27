package com.heowc;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringJUnitConfig(EndOfDayJobConfig.class)
class BatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void test(@Autowired Job job) throws Exception {
        jobLauncherTestUtils.setJob(job);
        JobExecution execution = jobLauncherTestUtils.launchJob();
        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
    }
}
