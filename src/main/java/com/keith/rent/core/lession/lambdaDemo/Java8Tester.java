package com.keith.rent.core.lession.lambdaDemo;

import com.keith.rent.core.po.Student;
import com.keith.rent.core.po.enum_demo.SpecialityEnum;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class Java8Tester {

    public static void main(String[] args) {
        System.out.println("使用 Java7 ：");
        //计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "dfg", "abcd", "", "jkl");
        System.out.println("列表：" + strings);
        long count = getCountEmptyStringUsingJava7(strings);
        System.out.println("空字符数量为: " + count);

        count = getCountLength3UsingJava7(strings);
        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings,", ");
        System.out.println("合并字符串: " + mergedString);

        // 获取列表元素平方数
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);


        List<String> nameStrs = Arrays.asList("Monkey", "Lion", "Giraffe","Lemur");

        List<String> list = nameStrs.stream()
                .filter(s -> s.startsWith("L"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);


        String[] array = {"Monkey", "Lion", "Giraffe", "Lemur"};
        Stream<String> nameStrs2 = Stream.of(array);
        System.out.println(nameStrs2);

        List<String> list1 = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");
        Stream<String> stream = list1.stream();

        Set<String> set = new HashSet<>(list1);
        Stream<String> stream1 = set.stream();

        final List<String> collect = stream.collect(Collectors.toList());
        final List<String> collect1 = stream1.collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);





    }






    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squareList = new ArrayList<>();
        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());
//            if (!squareList.contains(square)) {
                squareList.add(square);
//            }
        }
        return squareList;
    }


    public static boolean test2(List<Student> students) {
        String name = "xiangyang";
        students.removeIf(student -> !name.equals(student.getName()));
        return true;
    }

    private static String getMergedStringUsingJava7(List<String> list, String separator) {
        StringBuffer sb = new StringBuffer();
        for (String string : list) {
            if (!string.isEmpty()) {
                sb.append(string);
                sb.append(separator);
            }
        }
        return sb.toString().substring(0, sb.length() - 2);
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> list) {
        List<String> list1 = new ArrayList<>(list);
        for (int i = list1.size()-1; i >=0; i--) {
            String string = list1.get(i);
            if (string.isEmpty()) {
                list1.remove(string);
            }
        }
        return list1;
    }

    private static long getCountLength3UsingJava7(List<String> list) {
        int count = 0;
        if (list.size()<=0) return 0;
        for (String string : list) {
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static long getCountEmptyStringUsingJava7(List<String> list) {
        int count = 0;
        if (list.size() <= 0) {
            return 0;
        }
        for (String string : list) {
            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }
}
