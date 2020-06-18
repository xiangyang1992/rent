package com.keith.rent.core.quartz;

import com.keith.rent.core.task.TenantSyncJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class MainScheduler {


    public static void main(String[] args) {
        JobDetail jobDetail = JobBuilder.newJob(TenantSyncJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("name", "sads")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
                        .repeatForever()).build();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
