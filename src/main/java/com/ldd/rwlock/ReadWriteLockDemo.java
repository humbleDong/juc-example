package com.ldd.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author ldd
 * @Date 2024/4/11
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //无锁的
//        MyCache myCache = new MyCache();
//        for (int i = 1; i <= 5; i++) {
//            final int temp=i;
//            new Thread(()->{
//                myCache.put(temp+"",temp);
//            },String.valueOf(i)).start();
//        }
//        for (int i = 1; i <= 5; i++) {
//            final int temp=i;
//            new Thread(()->{
//                myCache.get(temp+"");
//            },String.valueOf(i)).start();
//        }

        //有锁的
        MyCacheLock myCacheLock = new MyCacheLock();
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCacheLock.put(temp+"",temp);
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCacheLock.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String,Object> map = new HashMap<>(0);
    //存
    public void put(String key,Object value) {
        System.out.println(Thread.currentThread().getName()+"写入:"+value);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"写入成功");
    }
    //取
    public void get(String key) {
        System.out.println(Thread.currentThread().getName()+"读取:"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取成功");
    }
}

//加锁的
class MyCacheLock {
    private volatile Map<String,Object> map = new HashMap<>(0);
    //读写锁更加细粒度的控制
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //private Lock lock1 = new ReentrantLock();
    //存
    public void put(String key,Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入:"+value);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入成功");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }
    //取
    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取:"+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取成功");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}