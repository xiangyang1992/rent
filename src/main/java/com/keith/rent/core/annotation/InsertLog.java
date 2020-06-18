package com.keith.rent.core.annotation;

import java.lang.annotation.*;

/**
 * 自定义注释
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InsertLog {

    String value() default "";//数据

    String module() default "";//模块
}
