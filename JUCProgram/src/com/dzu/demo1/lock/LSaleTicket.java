package com.dzu.demo1.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by ZhaoDong
 * @Classname LSaleTicket
 * @Description 使用Lock接口
 * @Date 2021/10/17 16:45
 */
public class LSaleTicket {
    public static void main(String[] args) {
        LTicket lTicket = new LTicket();
        new Thread(() -> {
            for (int i = 0; i < 40000; i++) {
                lTicket.sale();
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 40000; i++) {
                lTicket.sale();
            }
        },"BB").start();
    }

}

class LTicket {
    private final Lock lock = new ReentrantLock();
    private int number = 30000;

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了:" + number-- + "还剩：" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
