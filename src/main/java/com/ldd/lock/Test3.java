package com.ldd.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/10
 * 研究锁的现象
 * 测试 5和6
 */
public class Test3 {
    public static void main(String[] args) {
        //两个对象的class模板只有一个，static，锁的是class
        Person3 phone1 = new Person3();
        Person3 phone2 = new Person3();
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
class Person3{
    //synchronized锁的对象是方法的调用者
    //static 静态方法 类一加载就有了！class模板,锁的是class对象Class<Phone3> phone3Class = Phone3.class;
    //两个方法用的是同一个锁
    public static synchronized void eat() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("吃饭");
    }
    public static synchronized void sleep() {
        System.out.println("睡觉");
    }
}

