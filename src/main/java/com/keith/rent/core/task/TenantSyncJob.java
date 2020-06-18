package com.keith.rent.core.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Slf4j
public class TenantSyncJob implements Job {

    private static Boolean isRun = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        synchronized (isRun) {
            //如果正在运行，直接结束
            if (isRun) {
                log.info("上一次同步还未结束，这次直接跳过");
                return;
            } else {
                isRun = true;
            }
        }
        log.info("同步任务开始:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("------------同步中---------------");
        log.info("同步任务结束:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }
}
