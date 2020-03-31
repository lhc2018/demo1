package com.example.demo;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-02-26 21:34:38
 **/
public class W {
    /**
     * 类的加载过程，首先通过编译器将.java文件编译为.class文件
     * 通过classload加载到内存中。
     * 然后进行初始化。分两个阶段。第一个阶段就是  vilidator(校验) --preparement(准备，准备阶段---静态变量赋值
     * 将变量赋默认值int =0 object=null)----- 然后执行initailize(阶段)
     *  volatile关键字 保证变量的原子性  可见性  有序性  所以在单例中。定义静态变量要volatile修饰
     *  STW  stop the world
     *  jdk1.6之后  锁进行了一些优化  (第一个线程获取到的锁)偏向锁  （如果又其他线程来竞争锁。
     *  就变成轻量级锁（自旋锁是轻量级锁的一种实现。一般的轻量级锁都是自旋锁））轻量级锁   (默认尝试10次如果都没有获取到)重量级锁
     * @param args
     */

    public static void main(String[] args){
        System.out.println(W.count);
    }

    public static W t = new W();
    public static int count = 2;


    private W(){
        count++;
    }

}
