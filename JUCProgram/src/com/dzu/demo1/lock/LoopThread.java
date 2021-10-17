package com.dzu.demo1.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by ZhaoDong
 * @Classname LoopThread
 * @Description 定制化通信
 * notify
 * @Date 2021/10/17 21:41
 */
public class LoopThread {
    public static void main(String[] args) throws InterruptedException {
        ShareSource shareSource = new ShareSource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }

}
class ShareSource{
    private int flag = 1;
    private final Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "轮数：" + loop);
            }
            flag = 2;

            c2.signal();

        }finally {
            lock.unlock();
        }

    }
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "轮数：" + loop);
            }
            flag = 3;

            c3.signal();

        }finally {
            lock.unlock();
        }

    }
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "轮数：" + loop);
            }
            flag = 1;

            c1.signal();

        }finally {
            lock.unlock();
        }

    }
}
