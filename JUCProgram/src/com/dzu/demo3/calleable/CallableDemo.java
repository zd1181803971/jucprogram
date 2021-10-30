package com.dzu.demo3.calleable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author by ZhaoDong
 * @Classname CalleableDemo
 * @Description callable接口
 * 互斥条件
 * 请求和保持
 * 不剥夺
 * 环形等待
 * @Date 2021/10/19 20:08
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread(new MyThread1(),"AA").start();

        // 使用FutureTask
//        new Thread(new MyThread2(),"AA").start();
        FutureTask<Integer> futureTask = new FutureTask(new MyThread2());
        FutureTask<Integer> futureTask2 = new FutureTask(()-> 2);
        // futureTask原理  未来任务
        new Thread(futureTask,"FF2").start();

        while (!futureTask.isDone()) {
            System.out.println("wait...");
        }
        Integer integer = futureTask.get();
        System.out.println(integer);


    }
}

class MyThread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

class MyThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        return 200;
    }
}
