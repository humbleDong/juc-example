package com.ldd.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/10
 * 研究锁的现象
 * 测试 1和2
 */
public class Test1 {
    public static void main(String[] args) {
        Person person = new Person();
        new Thread(()->{
            person.eat();
        },"A").start();

        //休息一秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            person.sleep();
        },"B").start();
    }
}
class Person{
    //synchronized锁的对象是方法的调用者
    //两个方法用的是同一个锁,谁先拿到谁先执行
    public synchronized void eat() {
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
