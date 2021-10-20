package com.dzu.demo4.other;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author by ZhaoDong
 * @Classname CountDownDemo
 * @Description 计数锁
 * 只要满足计数器等于0：
 * 等待的就会运行.
 *
 * @Date 2021/10/20 21:11
 */
public class CountDownDemo {
    // 6个同学陆续离开教室之后，班长锁门
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("qweqweqwe");
        }).start();
        // 主线程会等待。
        // 只有countDownLatch里的count变为0，才会继续
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长锁门");



    }
}
