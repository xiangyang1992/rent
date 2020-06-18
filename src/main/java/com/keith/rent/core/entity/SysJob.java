package com.keith.rent.core.entity;

import java.io.Serializable;

/**
 * 定时任务(SysJob)实体类
 *
 * @author xiangyang
 * @since 2020-06-05 17:48:08
 */
public class SysJob implements Serializable {
    private static final long serialVersionUID = 368427760749572724L;
    
    private Integer taskId;
    
    private String taskName;
    
    private String cron;
    
    private String className;
    
    private String methodName;
    
    private Integer type;
    /**
     * 是否删除，0为可执行，1为不可执行
     */
    private Integer isDeleted;
    
    private String createBy;
    /**
    * 定时任务描述
    */
    private Integer taskDec;
    /**
    * 定时任务状态
    */
    private Integer status;


    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getTaskDec() {
        return taskDec;
    }

    public void setTaskDec(Integer taskDec) {
        this.taskDec = taskDec;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}