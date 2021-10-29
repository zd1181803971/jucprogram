package com.dzu.demo6.pool;

import java.util.concurrent.*;

/**
 * @author by ZhaoDong
 * @Classname ThreadPoolDemo
 * @Description 线程池
 * @Date 2021/10/28 22:29
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        // 一个池 5个线程
//        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 阿里巴巴规范建立创建线程的方法
        ExecutorService pool2 = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 一池一线程
//        ExecutorService pool = Executors.newSingleThreadExecutor();

        // 弹性扩容
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 11; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":123");
            });
        }
        pool.shutdown();
    }
}
