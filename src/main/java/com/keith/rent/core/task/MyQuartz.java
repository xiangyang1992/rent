package com.keith.rent.core.task;

import com.keith.rent.core.entity.Tenant;
import com.keith.rent.core.service.TenantService;
import com.keith.rent.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Slf4j
public class MyQuartz extends QuartzJobBean {


    @Autowired
    TenantService tenantService;
    /**
     * 执行定时任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Tenant> tenants = null;
        try {
            tenants = tenantService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String currentTime = DateUtil.getCurrentTimestamp(DateUtil.DEF_DATE);
        if (tenants.isEmpty()) {
            log.info("没有住户信息....");
        } else {
            for (Tenant tenant : tenants) {
                Date outTime = tenant.getCheckOutTime();
                String leaveTime = DateUtil.format(outTime, DateUtil.DEF_DATE);
                if (DateUtil.compareDate(currentTime, leaveTime)) {
                    log.info("该住户合同即将到期，请注意续租....{}", tenant.getTenantName());
                }
            }
        }
    }
}
