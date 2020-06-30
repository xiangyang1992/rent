package com.keith.rent.core.lession.lambdaDemo;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: keith
 */
public class FunExpression_Demo {


    public static void main(String[] args) {
        FunExpression_Demo.test1();
    }


    public static void test1() {
//        Predicate<Integer> predicate = x -> x == 303;
//        Tenant tenant = new Tenant("xiangyang", 303);
//        System.out.println(
//                "xiangyang住的是几号房间？" + predicate.test(tenant.getRoomId())
//        );
//        Function<Tenant, Integer> function = Tenant::getRoomId;
//        Integer name = function.apply(tenant);
//        System.out.println(name);

        test(() -> "我是一个演示的函数式接口");
    }


    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);

    }

    public interface Worker{
        String work();
    }
}
