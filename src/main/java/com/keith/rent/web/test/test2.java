package com.keith.rent.web.test;

import com.keith.rent.core.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class test2 {
    @Autowired
    TenantService tenantService;
    public static void main(String[] args) {

//        String str = "adsasdas";
//        String[] strings = str.split("");
//        for (String string : strings) {
//            System.out.println(string);
//        }
//
//        List<Tenant> list = new ArrayList<>();
//        Set<Integer> set  = list.stream().map(e -> e.getRoomId()).collect(Collectors.toSet());
//
//        String[] ids = {"121", "2323", "32"};


        Map<String, Object> map = new HashMap<>();
        map.put("1", 123);
        map.put("2", 345);
        map.put("3", "向阳");
//        for (String s : map.keySet()) {
//            System.out.println(s);
//        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Map:" + entry.getKey() + " Value:" + entry.getValue());
        }

        map.forEach((k, v) -> {
            System.out.println("map:" + k + ",value:" + v);
        });

        map.forEach((k, v) -> {
            System.out.println("map:" + k + ",value:" + v);
            if ("3".equals(k)) {
                System.out.println("hello 向阳");
            }
        });

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("夕阳");
        list.forEach(item->{
            if ("3".equals(item)) {
                System.out.println("OK");
            }
        });

        list.forEach(System.out::println);
        list.stream()
                .filter(s -> s.contains("3"))
                .forEach(System.out::println);
    }
}
