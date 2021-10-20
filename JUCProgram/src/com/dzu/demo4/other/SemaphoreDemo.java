package com.dzu.demo4.other;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author by ZhaoDong
 * @Classname SemaphoreDemo
 * @Description 信号量
 * @Date 2021/10/20 22:16
 */
public class SemaphoreDemo {

    // 6辆汽车，三个停车位
    public static void main(String[] args) {
        // 创建Semaphore，设置许可数量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了停车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "离开停车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
