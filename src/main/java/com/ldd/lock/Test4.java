package com.ldd.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/10
 * 研究锁的现象
 * 测试 7和8
 */
public class Test4 {
    public static void main(String[] args) {
        Person4 phone1 = new Person4();
        Person4 phone2 = new Person4();
        new Thread(()->{
            phone1.eat();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone2.sleep();
        },"B").start();
    }
}
class Person4{
    public static synchronized void eat() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("吃饭");
    }
    public synchronized void sleep() {
        System.out.println("睡觉");
    }
}

