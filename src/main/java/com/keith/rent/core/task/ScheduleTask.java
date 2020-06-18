package com.keith.rent.core.task;

import com.keith.rent.core.common.SpringContextHelper;
import com.keith.rent.core.entity.SysJob;
import com.keith.rent.core.service.SysJobService;
import com.keith.rent.core.utils.DateUtil;
import com.keith.rent.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Slf4j
//@Component
public class ScheduleTask implements SchedulingConfigurer {

    @Autowired
    SysJobService sysJobService;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<SysJob> tasks = getAllScheduleTask();
        log.info("【定时任务启动】启动任务数：" + tasks.size() + "; time = " + DateUtil.getCurrentDefDate());
        //校验数据
        checkDataList(tasks);
        //通过检验的数据执行定时任务
        int count = 0;
        if (tasks.size() > 0) {
            for (SysJob task : tasks) {
                try {
                    taskRegistrar.addTriggerTask(getRunable(task),getTrigger(task));
                    count++;
                } catch (Exception e) {
                    log.error("task start error :" +task.getTaskName());
                }
            }
            log.info("started task number= " + count + "; time=" + DateUtil.getCurrentDefDate());
        }
    }

    private Trigger getTrigger(SysJob task) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(task.getCron());
                Date nextExec = cronTrigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };
    }

    private Runnable getRunable(SysJob task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Object object = SpringContextHelper.getBean(task.getClassName());
//                    Method method = object.getClass().getMethod(task.getMethodName());
                    Method[] method = object.getClass().getMethods();
                    try {
                        for (Method method1 : method) {
                            String name = method1.getName();
                            if (name.equals(task.getMethodName())) {
                                method1.invoke(object,"");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        log.error("refect exception:" + task.getClassName() + ";" + task.getMethodName() + e.getMessage());
                    } catch (InvocationTargetException e) {
                        log.error(e.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private List<SysJob> checkDataList(List<SysJob> tasks) {
        StringBuilder msg = new StringBuilder();
        for (SysJob task : tasks) {
            if ("ok".equals(checkOneData(task))) {
                msg.append(task.getTaskName()).append(";");
                tasks.remove(task);
            }
        }
        if (!StringUtil.isNull(msg)) {
            msg = msg.insert(0, msg);
            log.error(msg.toString());
        }
        return tasks;
    }

    private String  checkOneData(SysJob task) {
        String result = "ok";
        Class cls = null;
        try {
            cls = Class.forName(task.getClassName());
            Object object = SpringContextHelper.getBean(cls);
            Method method = object.getClass().getMethod(task.getMethodName(), null);
            String cron = task.getCron();
            if (StringUtil.isNull(cron)) {
                result = "no found the cron" + task.getTaskName();
                log.error(result);
            }
        } catch (ClassNotFoundException e) {
            result = "not found the class " + task.getClassName();
            log.error(result);
        } catch (NoSuchMethodException ee) {
            result = "not found the method: " + task.getClassName() + ";" + task.getMethodName();
            log.error(result);
        }
        return result;
    }

    private List<SysJob> getAllScheduleTask() {
        SysJob sysJob = new SysJob();
        sysJob.setIsDeleted(0);
        List<SysJob> sysJobs = sysJobService.selectTask(sysJob);
        return sysJobs;
    }

}
