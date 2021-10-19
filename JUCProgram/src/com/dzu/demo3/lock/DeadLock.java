package com.dzu.demo3.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author by ZhaoDong
 * @Classname DeadLock
 * @Description 死锁
 *
 *所谓死锁，是指多个线程在运行过程中因争夺资源而造成的一种僵局，当进程处于这种僵持状态时，若无外力作用，它们都将无法再向前推进
 *
 *  产生死锁的必要条件：
 *      互斥条件：
 *      请求和保持条件
 *      不剥夺条件
 *      环路等待条件
 *
 *      jps++jstack
 * @Date 2021/10/19 19:31
 */
public class DeadLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(()->{
            synchronized (a) {
                System.out.println(Thread.currentThread().getName()+"AAA得到a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName()+"AAA得到b");

                }
            }
        },"AAA").start();

        new Thread(()->{
            synchronized (b) {
                System.out.println(Thread.currentThread().getName()+"BBB得到b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName()+"BBB得到a ");

                }
            }
        },"BBB").start();
    }
}
