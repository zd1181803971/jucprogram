package com.dzu.demo1.sync;

/**
 * @author by ZhaoDong
 * @Classname ThreadCommunication
 * @Description 通过synchronized实现线程通信
 * 1\ 使用IF判断会出现虚假唤醒问题：
 * 在if块中使用wait方法，是非常危险的，因为一旦线程被唤醒，并得到锁，就不会再判断if条件，
 * 而执行if语句块外的代码，所以建议，凡是先要做条件判断，再wait的地方，都使用while循环来做
 * @Date 2021/10/17 17:11
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        ThreadDemo1 demo = new ThreadDemo1();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    demo.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    demo.uncr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    demo.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    demo.uncr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}

class ThreadDemo1 {
    private int num = 0;

    public synchronized void incr() throws InterruptedException {
        if (num == 1) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "::" + ++num);
        notifyAll();
    }
    public synchronized void uncr() throws InterruptedException {
        if (num == 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "::" + --num);
        notifyAll();
    }
}
