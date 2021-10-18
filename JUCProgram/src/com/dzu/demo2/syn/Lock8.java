package com.dzu.demo2.syn;


import java.util.concurrent.TimeUnit;

/**
 * @author by ZhaoDong
 * @Classname Lock8
 * @Description 演示锁
 *
 *  对于普通同步方法：锁的是当前实例
 *  对于静态同步方法，锁的是当前类的Class对象
 *  对于同步方法快：锁是synchronized括号里配置的对象
 *
 * @Date 2021/10/18 21:10
 */
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.getHello();
        }, "AA").start();
    }
}

class Phone {
    public synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);

        System.out.println("sendSMS");
    }

    public synchronized void sendEmail() {
        System.out.println("sendEmail");
    }

    public void getHello() {
        System.out.println("getHello");
    }
}

