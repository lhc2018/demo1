package com.example.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: demo
 * @description: 读写锁
 * @author: liying
 * @create: 2020-02-27 18:28:25
 **/
public class ReadWriteLockTest {

    private int number = 0;

    public ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock lock = readWriteLock.readLock();
    private Lock wlock = readWriteLock.writeLock();

    public static void main(String[] args){
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        readWriteLockTest.write();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"写锁").start();
        }
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockTest.read();
                }
            },"读锁").start();
        }
    }


    public void read(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+":"+number);
        lock.unlock();
    }

    public int write() throws InterruptedException {
        wlock.lock();
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName()+":"+ ++number);
        wlock.unlock();
        return number;
    }
}
