package org.example;

import org.quartz.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
class QuartzJobsConfig {

    @Bean
    ApplicationRunner shortenLifeCycleJob(SchedulerFactoryBean schedulerFactoryBean) {
        return args -> {
            // Job 생성
            final JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).build();

            // Trigger 생성
            final Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                    .build();

            try {
                schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
