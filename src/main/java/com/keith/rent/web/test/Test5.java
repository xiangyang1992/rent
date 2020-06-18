package com.keith.rent.web.test;

import java.util.UUID;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class Test5 {

    static int count = 5789;
    static int pageSize = 5000;



    public static int mathTest() {
        int pageCount =  count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        int a = count % pageSize;
        System.out.println(a);
        return pageCount;


    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        System.out.println(Test5.mathTest());
    }
}
