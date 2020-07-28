package com.keith.rent.web.test;

import com.google.common.collect.Lists;
import com.keith.rent.core.po.Student;
import com.keith.rent.core.po.enum_demo.SpecialityEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description:
 * author:
 */
public class Test7 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("1", "3", "4", "5");
        List<String> list1 = new ArrayList<>(list);
        list1.add("6");
//        System.out.println(list1);

        BigDecimal decimal = new BigDecimal("2.00");
//        System.out.println(decimal.subtract(new BigDecimal("1.10")));

//        System.out.println(12345 + 5432l);

        Student student = new Student("xy", 15, 170, SpecialityEnum.DANCE);
        Student student1 = new Student("k", 21, 170, SpecialityEnum.SWIMMING);
        Student student2 = new Student("t", 23, 170, SpecialityEnum.SING);
        Student student3 = new Student("uy", 16, 170, SpecialityEnum.SWIMMING);
        List<Student> students = Lists.newArrayList(student, student1, student2, student3);
        List<Student> students1 = Student.filterStudent(students, Student::isAgeGreaterThan20);
        List<Student> students2 = Student.filterStudent(students, Student::statureFilter);
        System.out.println(students1);
        System.out.println(students2);
    }
}
