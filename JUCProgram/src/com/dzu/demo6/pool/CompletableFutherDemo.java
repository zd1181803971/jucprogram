package com.dzu.demo6.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author by ZhaoDong
 * @Classname CompletableFutherDemo
 * @Description 异步回调
 * @Date 2021/10/30 14:57
 */
public class CompletableFutherDemo {
    public static void main(String[] args) throws Exception {
        // 异步调用，无返回值
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":1");
        });
        future1.get();

        // 异步调用，有返回值
        CompletableFuture<Integer> fu = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":2");
//           int i =  1 / 0;
            return 2;
        });
        fu.whenComplete((t, u) -> {
            System.out.println(t);
            System.out.println(u);
        });
    }
}
