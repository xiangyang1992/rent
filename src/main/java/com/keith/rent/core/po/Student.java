package com.keith.rent.core.po;

import com.keith.rent.core.po.enum_demo.SpecialityEnum;
import lombok.Data;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Data
public class Student {

    private String name;
    private int age;
    private int stature;
    /**
     * 兴趣爱好
     */
    private Enum<SpecialityEnum> specialities;

    public Student(String name, int age, int stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }

    public Student() {
    }

    public Student(String name, int age, int stature, Enum<SpecialityEnum> specialities) {
        this.name = name;
        this.age = age;
        this.stature = stature;
        this.specialities = specialities;
    }
}
