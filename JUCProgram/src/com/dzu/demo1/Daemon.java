package com.dzu.demo1;

/**
 * @author by ZhaoDong
 * @Classname Daemon
 * @Description 用户线程并不会结束JVM
 * @Date 2021/10/17 16:06
 */
public class Daemon {
    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "qwe");

        // 设置为守护线程
//        aa.setDaemon(true);

        aa.start();
        System.out.println(Thread.currentThread().getName() + "OVER");
    }
}
