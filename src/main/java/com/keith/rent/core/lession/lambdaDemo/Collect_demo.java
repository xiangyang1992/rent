package com.keith.rent.core.lession.lambdaDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class Collect_demo {

    public static void test1() {
//        List<Student> studentList = Stream.of(new Student("xiangyang", 22, 175),
//                new Student("keith",23,180),
//                new Student("潘艳桃",28,167)).collect(Collectors.toList());
//        for (Student student : studentList) {
//            System.out.println(student.getName());
//        }


        List<String> strings = Arrays.asList("abc", "cdsd", "asd", "dasd","");
        List<String> filter = strings.stream().filter(s -> s.contains("d")).collect(Collectors.toList());
        System.out.println(filter);

        long count = strings.stream().map(string -> string.isEmpty()).count();
        System.out.println(count);

    }


    public static void main(String[] args) {
        Collect_demo.test1();

//        long time = System.currentTimeMillis();
//        Random random = new Random();
//        random.ints().limit(100).parallel().forEach(System.out::print);
//        System.out.println("time1 = "+ (System.currentTimeMillis() - time));
//
//
//        time = System.currentTimeMillis();
//        random.ints().limit(100).forEach(System.out::print);
//        System.out.println("time2 = "+ (System.currentTimeMillis() - time));


    }

}
