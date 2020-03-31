package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-02-27 14:41:00
 **/
public class AoticTest {
//    private volatile int count = 1;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args){
        AoticTest aoticTest = new AoticTest();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        aoticTest.print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void print() throws InterruptedException {
        Thread.sleep(200);
        System.out.println(atomicInteger.getAndIncrement());
    }
}
