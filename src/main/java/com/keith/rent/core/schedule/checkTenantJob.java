package com.keith.rent.core.schedule;

import com.keith.rent.core.common.SpringContextHelper;
import com.keith.rent.core.entity.Tenant;
import com.keith.rent.core.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Slf4j
@Component
@EnableScheduling
public class checkTenantJob implements Job {

    private static TenantService tenantService = (TenantService) SpringContextHelper.getBean(TenantService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            List<Tenant> tenants = tenantService.queryAllByLimit(1, 10);
            log.info("查询到用户数：{}", tenants.size());
        } catch (Exception e) {
            log.error("查询失败");
        }

    }
}
