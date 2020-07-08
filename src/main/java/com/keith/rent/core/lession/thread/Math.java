package com.keith.rent.core.lession.thread;

import java.util.Random;

/**
 * description:
 * author:
 */
public class Math {


    public static void main(String[] args) {
        Runer runer = new Runer();
        runer.start();

        Runner2 liuxiang = new Runner2();
        Thread thread = new Thread(liuxiang);
        thread.start();
    }



}

class Runer extends Thread {
    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (int i = 1; i <= speed; i++) {
            this.setName("刘翔");
            System.out.println(this.getName()+"已前进"+(i*speed)+"米("+speed+"米/秒)");
        }
    }
}

class Runner2 implements Runnable {

    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (Integer i = 1; i <= speed; i++) {
            System.out.println(Thread.currentThread()+"已前进"+(i*speed)+"米("+speed+"米/秒)");
        }
    }
}

