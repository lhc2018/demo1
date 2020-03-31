package com.example.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-02-27 16:26:59
 **/
public class TestProductAndConsumer1 {



   /* public static Lock lock = new ReentrantLock();
    public static Condition condition1= lock.newCondition();
    public static Condition condition2= lock.newCondition();*/

    public static void main(String[] args){
        Boss boss = new Boss();
        new Thread(new Product(boss),"生产者1").start();
        new Thread(new Consumer(boss),"消费者1").start();
        /*new Thread(new Product(boss),"生产者2").start();
        new Thread(new Consumer(boss),"消费者2").start();*/

    }

    static class Boss{
        private  int pCount = 0;

        public synchronized void get() throws InterruptedException {
            while(pCount>=10){
                System.out.println("货已经满了。等会再生产吧");
                this.wait();//调用object的wait方法一定要再while循环中。
            }
            System.out.println(Thread.currentThread().getName()+":"+ ++pCount);
            this.notifyAll();
        }

        public synchronized void sail() throws InterruptedException {
            while (pCount<=0){
                System.out.println("缺货了，赶紧去进货吧");
                this.wait();//调用object的wait方法一定要再while循环中。
            }
            System.out.println(Thread.currentThread().getName()+":"+ --pCount);
            this.notifyAll();
        }
    }

    //定义一个生产者线程 雇佣老板去进货
    static class Product implements Runnable{

        private Boss boss;
        public Product(Boss boss){
            this.boss = boss;
        }

        @Override
        public void run() {
          for(int i=0;i<20;i++){
              try {
                  boss.get();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        }
    }

    //定义一个消费者线程雇佣老板去卖货
    static class Consumer implements Runnable{
        private Boss boss;
        public Consumer(Boss boss){
            this.boss = boss;
        }

        @Override
        public void run() {
          for(int i=0;i<20;i++){
              try {
                  boss.sail();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        }
    }

}
