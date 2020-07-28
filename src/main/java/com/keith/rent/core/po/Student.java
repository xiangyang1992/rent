package com.keith.rent.core.po;

import com.keith.rent.core.po.enum_demo.SpecialityEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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


    public static Predicate<Student> ageGreaterThan70 = student -> student.getAge() > 15;
    public static Predicate<Student> genderM = student -> student.getStature() > 170;

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

    public static boolean isAgeGreaterThan20(Student student) {
        return student.getAge() > 20;
    }

    public static boolean statureFilter(Student student) {
        return student.getSpecialities().compareTo(SpecialityEnum.SWIMMING) > 0;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Student> filterStudent(List<Student> students, Predicate<Student> studentPredicate) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (studentPredicate.test(student)) {
                result.add(student);
            }
        }
        return result;
    }
 }
