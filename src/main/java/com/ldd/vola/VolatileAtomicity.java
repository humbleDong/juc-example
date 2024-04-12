package com.ldd.vola;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ldd
 * @Date 2024/4/12
 * Volatile不保证原子性测试
 */
public class VolatileAtomicity {
    //private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger();
    public static void add() {
        //num++
        num.getAndIncrement();//获取上一个值并且自增1
    }
    public static void main(String[] args) {
        //理论上num结果应该为20000,加volatile还是不能加到2万,加Synchronized可以
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2) {//main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"--->"+num);
    }
}
