package com.keith.rent.web.test;

import com.keith.rent.core.entity.Tenant;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class Test4 extends Thread{

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("我的");
//        list.add("他的");
//        list.add("你的");
//        Set<String> set = list.stream().collect(Collectors.toSet());
//        Integer[] ids = {1, 4, 5, 6};
//
////        System.out.println(ids);
//        List<Integer> id = Arrays.asList(ids);
//        System.out.println(id);
////        System.out.println(set);
//    }

    static {
        Tenant tenant = new Tenant();
        tenant.setTenantId(5);
        tenant.setAddress("hubei");
        tenant.setApartmentId(304);
    }


    @Override
    public void run() {
        sout();
    }

    private static String USER_NAME_FORMAT = "$username={0}$";

    private static String getUserName(String userId) {
        return MessageFormat.format(USER_NAME_FORMAT, userId);
//        return String.format(USER_NAME_FORMAT, userId);
    }


    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void sout() {
        Date date = new Date();
        String format = df.get().format(date);
        System.out.println(format);
    }


    public static void main(String[] args) {
////        String userName = Test4.getUserName("13123");
////        System.out.println(userName);
//        Date date = new Date();
//
//        String newDate = df.get().format(date);
//        System.out.println(newDate);

        Test4 test4 = new Test4();
        test4.start();
        Test4 test41 = new Test4();
        test41.start();
    }
}
