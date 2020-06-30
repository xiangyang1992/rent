package com.keith.rent.web.test;

import org.springframework.util.Assert;

import java.util.stream.LongStream;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                threadLocal.set("xiangyang" + Thread.currentThread().getName());
//                String s = threadLocal.get();
//                System.out.println("threadName = " + Thread.currentThread().getName() + "[threadLocal = " + threadLocal + "\t data = " + s + "]");
//            },"threadName"+i).start();
//        }

//        IntStream.range(0, 100)
//                .parallel()
//                .forEach(value -> System.out.println(Thread.currentThread() + " " + value));

        //并行计算
        long time = System.currentTimeMillis();
        long sum1 = LongStream.rangeClosed(1, 10000000).parallel().sum();
        System.out.println(System.currentTimeMillis() - time);

        //串行计算
        time = System.currentTimeMillis();
        long sum2 = LongStream.rangeClosed(1, 10000000).sum();
        System.out.println(System.currentTimeMillis() - time);

        System.out.println("sum1 = " + sum1 + ",sum2 = " + sum2);
        Assert.isTrue(sum1 == sum2);

    }
}
