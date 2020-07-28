package com.keith.rent.core.config;

import com.keith.rent.core.task.MyQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail MyQuartzDetail() {
        return JobBuilder.newJob(MyQuartz.class).withIdentity("MyQuartz").storeDurably().build();
    }

    @Bean
    public Trigger MyQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(MyQuartzDetail())
                .withIdentity("MyQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
