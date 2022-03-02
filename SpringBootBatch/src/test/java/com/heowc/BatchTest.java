package com.heowc;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
class BatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void test() throws Exception {
        JobParameters parameters = jobLauncherTestUtils.getUniqueJobParameters();

        JobExecution execution = jobLauncherTestUtils.launchJob(parameters);

        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
    }
}
