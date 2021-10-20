package com.dzu.demo4.other;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author by ZhaoDong
 * @Classname CyclicBarrierDemo
 * @Description 循环栅栏
 *
 * 只要线程数满足，就会运行barrierAction。
 * 只要不满足，也就是线程数大于或者小于NUM，都会wait
 * @Date 2021/10/20 21:37
 */
public class CyclicBarrierDemo {
    private static final int NUM = 7;
    // 集齐七颗龙珠就可以召唤神龙
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier c = new CyclicBarrier(NUM,()->{
            System.out.println("集齐七颗龙珠就可以召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "集齐了");
                try {
                    c.await();
                    System.out.println(Thread.currentThread().getName()+"iop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
