package com.example.demo;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-02-27 12:46:45
 **/
public class S {

    /**
     * 分析一下需求。就是涉及到 线程间通信 定义一个变量  同步锁 Reentrantlock   Condition
     */
    public static Lock lock = new ReentrantLock();
    public static Condition condition1 = lock.newCondition();
    public static Condition condition2 = lock.newCondition();
    public static Condition condition3 = lock.newCondition();
    public static int number = 1;


    public static void main(String[] args){
        LoopPrint loopPrint = new LoopPrint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loopPrint.loopPrintA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loopPrint.loopPrintB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loopPrint.loopPrintC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class LoopPrint{

        public void loopPrintA() throws InterruptedException {
                lock.lock();
                if(number!=1){
                    condition1.await();
                }
                try {
                    System.out.println("A");
                    number = 2;
                    condition2.signal();//唤醒
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
        }

        public void loopPrintB() throws InterruptedException {
            lock.lock();
            if(number!=2){
                condition1.await();
            }
            try {
                System.out.println("B");
                number = 3;
                condition3.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }


        public void loopPrintC() throws InterruptedException {
            lock.lock();
            if(number!=3){
                condition1.await();
            }
            try {
                System.out.println("C");
                number = 1;
                condition1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }


}
