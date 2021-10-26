package com.dzu.demo5.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author by ZhaoDong
 * @Classname ReadWriteLock
 * @Description 读写锁 案例
 * 一定能读到最新的数据
 * 修改期间，写锁是一个排他锁（互斥锁、独享锁）
 * 读锁是一个共享锁
 * 写锁没释放，读就必须等待
 * 读 + 读； 无锁的状态，并发读只会在redis中记录好当前的所有读锁，都会加锁成功。共享锁 大家都能用
 * 写 + 读； 等待写锁释放
 * 写 + 写； 阻塞的方式
 * 读 + 写； 有读锁，写也需要等待
 * 只要有写的存在，无论前后，都必须等待 【排他锁
 * @Date 2021/10/26 21:26
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyChe myChe = new MyChe();
        for (int i = 0; i < 5; i++) {
            // 匿名内部类访问外部局部变量必须是final修饰的
            // jdk1.8 把它默认设置为final的了。
            int num = i;
            new Thread(()->{
                myChe.put(num +"", num +"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            int num = i;
            new Thread(()->{
                myChe.get(num +"");
            },String.valueOf(i)).start();
        }
    }
}

class MyChe {
    private volatile Map<String, Object> map = new HashMap<>();

    // 读写锁对象
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key, Object value) {
        rwLock.writeLock().lock();

        System.out.println(Thread.currentThread().getName() + "正在写操作" + key);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写完了" + key);
        rwLock.writeLock().unlock();
    }

    public Object get(String key){
        rwLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "正在读操作" + key);

        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读完了" + key);
        rwLock.readLock().unlock();
        return result;

    }

}

