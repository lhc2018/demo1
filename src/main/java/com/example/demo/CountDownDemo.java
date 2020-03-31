package com.example.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @program: demo
 * @description: 多线程异步阻塞设计
 * @author: liying
 * @create: 2020-02-27 15:44:17
 **/
public class CountDownDemo {

    public static void main(String[] args) throws InterruptedException {
        //设计一个闭锁。也就是让所有的价格统计 数量统计  重量统计都完成以后。然后再去算价格
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Factory factory = new Factory();
        for(int i=0;i<3;i++){
            if(i==0){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        factory.countNum();
                        countDownLatch.countDown();
                    }
                }).start();
            } else if(i==1){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        factory.countPrice();
                        countDownLatch.countDown();
                    }
                }).start();
            } else if(i==2){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        factory.countTime();
                        countDownLatch.countDown();
                    }
                }).start();
            }

        }
        countDownLatch.await();

        System.out.println("统计所有的数据");

    }


    static class Factory{

        public void countPrice(){
            System.out.println("价格是1000");
        }

        public void countNum(){
            System.out.println("数量是10000");
        }

        public void countTime(){
            System.out.println("重量是100kg");
        }

    }

}
