package com.ldd.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/10
 * 研究锁的现象
 * 测试 3和4
 */
public class Test2 {
    public static void main(String[] args) {
        Person2 person1 = new Person2();
        Person2 person2 = new Person2();
        new Thread(()->{
            person1.eat();
        },"A").start();

        //休息一秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            person2.sleep();
        },"B").start();

//        new Thread(()->{
//            person.hello();
//        },"C").start();
    }
}
class Person2{
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
    public void hello() {
        System.out.println("你好");
    }
}
