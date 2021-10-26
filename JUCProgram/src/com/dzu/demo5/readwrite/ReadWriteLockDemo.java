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

