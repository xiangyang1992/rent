package com.keith.rent.core.po;

import lombok.Data;

import java.util.List;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: keith
 *
 * 班级类
 */
@Data
public class OutstandingClass {

    /**
     * 班级名称
     */
    private String name;
    /**
     * 班级学生
     */
    private List<Student> students;
}
