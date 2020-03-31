package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @program: demo
 * @description: FutureTask 带返回值的线程任务
 * @author: liying
 * @create: 2020-02-27 16:06:47
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask =new FutureTask(myCallable);
        new Thread(futureTask).start();
        Integer o = (Integer)futureTask.get();
        System.out.println("0:"+o);
    }


    static class MyCallable implements Callable<Integer> {
        private int number = 0;
        @Override
        public Integer call() throws Exception {
            for(int i=0;i<100;i++){
                number++;
            }
            return number;
        }
    }
}
