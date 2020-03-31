package com.example.demo;

import sun.dc.pr.PRError;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-02-27 16:26:59
 **/
public class TestProductAndConsumer {

    private static int pCount = 0;

    public static Lock lock = new ReentrantLock();
    public static Condition condition1= lock.newCondition();
    public static Condition condition2= lock.newCondition();

    public static void main(String[] args){
        Boss boss = new Boss();
        new Thread(new Product(boss),"生产者1").start();
        new Thread(new Consumer(boss),"消费者1").start();
        new Thread(new Product(boss),"生产者2").start();
        new Thread(new Consumer(boss),"消费者2").start();

    }

    static class Boss{
        public void get(){
            try {
                lock.lock();//上锁
                while (pCount>=10){
                    System.out.println("货已经满了。等会再生产吧");
                    try {
                        condition1.await();//要写在while循环中。否则会出现虚假唤醒的可能。
                        //如果用if。启动两个线程没有问题。但是启动多个线程就会出问题
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":"+ ++pCount);
                condition2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void sail(){
            try{
                lock.lock();
                while (pCount<=0){
                    System.out.println("缺货了，赶紧去进货吧");
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":"+ --pCount);
                condition1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
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
          while (true){
              boss.get();
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
          while (true){
              boss.sail();
          }
        }
    }

}
