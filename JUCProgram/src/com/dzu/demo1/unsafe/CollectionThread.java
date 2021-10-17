package com.dzu.demo1.unsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author by ZhaoDong
 * @Classname CollectionThread
 * @Description List集合线程不安全
 * @Date 2021/10/17 21:57
 */
public class CollectionThread {
    public static void main(String[] args) {
        // 线程安全
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        List<String> list2 = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    }

    public void ConcurrentModificationException() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                arrayList.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(arrayList);
            }, String.valueOf(i)).start();
        }
    }
}
