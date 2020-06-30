package com.keith.rent.web.test;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class Test6 {


    public static void test1() {
//        List<Integer> list = new ArrayList<>();
//
//        for (int i = 0; i < 50; i++) {
//            list.add(i);
//            System.out.println(list);
//        }

        Integer a = 1;
        System.out.println(a == 1 ? "true" : "false");
        Boolean bool = false;
        System.out.println(bool ? "true":"false");
    }

    public static void test2() {
        String s = "abcd";
        String s1 = s;
        s = s.concat("ed");
        System.out.println(s);
    }


    public static void main(String[] args) {
//        Test6.test1();
        Test6.test2();
    }
    
}
