package com.keith.rent.web.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class DownloadsSample {


    public static int users = 100;    //同时模拟的并发访问用户数量
    public static int downTotal = 50000; //用户下载的真实总数
    public static int count = 0;//计数器


    public static void main(String[] args) {
        //调度器
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(users);
        for (int i = 0; i < downTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
        try {
            Thread.sleep(3000);
            executorService.shutdown();
            System.out.println("总下载数：" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void add() {
        count++;
    }
}
