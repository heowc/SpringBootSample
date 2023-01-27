package com.heowc;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SimpleJobLauncherController {

    private final JobLauncher jobLauncher;
    private final Job job;

    public SimpleJobLauncherController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @RequestMapping("/jobLauncher.html")
    public void handle() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        final Map<String, JobParameter<?>> map = new HashMap<>();
        map.put("create_date", new JobParameter<>(LocalDateTime.now().toString(), String.class));
        jobLauncher.run(job, new JobParameters(map));
    }
}
