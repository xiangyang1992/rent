package com.keith.rent.core.lession.lambdaDemo;

import com.keith.rent.core.po.Student;
import com.keith.rent.core.po.enum_demo.SpecialityEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 * author:
 */
public class StreamFilterPredicate {

    public static void main(String[] args) {

        Student xiangyang = new Student("xiangyang", 22, 170, SpecialityEnum.SING);
        Student panyangtao = new Student("panyantao", 23, 165, SpecialityEnum.DANCE);
        Student zhangsan = new Student("zhangsan", 24, 190, SpecialityEnum.SWIMMING);

        List<Student> students = Arrays.asList(xiangyang, panyangtao, zhangsan);

        List<Student> filtered = students.stream()
                .filter(Student.ageGreaterThan70.and(Student.genderM))
                .collect(Collectors.toList());
        System.out.println(filtered);
    }


}
