package com.keith.rent.core.aspect;

import com.alibaba.fastjson.JSONObject;
import com.keith.rent.core.annotation.InsertLog;
import com.keith.rent.core.entity.SysLog;
import com.keith.rent.core.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */

@Aspect
@Component
public class SysInsertLogAspect {


    @Autowired
    SysLogService sysLogService;

    private static ThreadLocal<ProceedingJoinPoint> p = new ThreadLocal<>();

    @Pointcut("@annotation(com.keith.rent.core.annotation.InsertLog)")
    public void InsertLog() {

    }

    @Around("InsertLog()")
    public Object around(ProceedingJoinPoint proint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proint.proceed();
        long time = System.currentTimeMillis() - startTime;
        try {
            saveLog(proint, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(ProceedingJoinPoint proint, long time)throws Exception {
        MethodSignature signature = (MethodSignature) proint.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getLocalAddr();
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setTime(time);
        sysLog.setCreateTime(Calendar.getInstance().getTime());
        InsertLog insertLog = method.getAnnotation(InsertLog.class);
        if (insertLog != null) {
            sysLog.setOperation(insertLog.module());
        }
        //请求的方法和参数
        String classname = proint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setClassName(classname);
        sysLog.setMethod(methodName);
        Object[] objects = proint.getArgs();
        StringBuilder sb = new StringBuilder();
        if (objects.length > 0) {
            for (Object object : objects) {
                if (object instanceof HttpServletRequest || object instanceof HttpServletResponse ||
                object instanceof HttpSession) {
                    object = "";
                }
                sb.append(JSONObject.toJSON(object));
            }
        }
        sysLog.setParams(sb.toString());
        sysLogService.insert(sysLog);
    }
}
