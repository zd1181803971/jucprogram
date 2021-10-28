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
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ExecutorService pool2 = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 11; i++) {
            pool.execute(() -> {
                System.out.println("123");
            });

        }
    }
}
