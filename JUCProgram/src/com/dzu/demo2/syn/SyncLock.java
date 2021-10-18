package com.dzu.demo2.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by ZhaoDong
 * @Classname SyncLock
 * @Description 可重入锁\递归锁
 * @Date 2021/10/18 21:58
 */
public class SyncLock {

    public static void main(String[] args) {
        new SyncLock().add();
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "1");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "2");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "3");
                    }
                }
            }
        }, "aa").start();
    }

    public void ReentrantLockTest() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "1");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "2");
            } finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }

    }

    public synchronized void add() {
        add();
    }
}
