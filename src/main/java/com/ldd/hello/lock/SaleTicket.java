package com.ldd.hello.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ldd
 * @Date 2024/4/10
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //Runnable接口为函数式接口
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"b").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"c").start();
    }
}

//使用OOP方式
class Ticket{
    //定义一个票的数量
    private int number=50;
    Lock lock= new ReentrantLock();

    //卖票的方法
    public void sale(){
        lock.lock();//加锁
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(51-number)+",现在还剩"+(--number)+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();//解锁
        }
    }
}
