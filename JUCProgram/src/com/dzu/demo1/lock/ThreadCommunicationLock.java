package com.dzu.demo1.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by ZhaoDong
 * @Classname ThreadCommunicationLock
 * @Description 通过lock接口实现线间的通信
 * @Date 2021/10/17 21:17
 */
public class ThreadCommunicationLock {
    public static void main(String[] args) {

    }
}
class ThreadLock{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increase() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "::" + ++num);
            condition.signalAll();

        }finally {
            lock.unlock();
        }
    }
    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            while (num == -1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "::" + ++num);
            condition.signalAll();


        }finally {
            lock.unlock();
        }
    }

}

