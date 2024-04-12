package com.ldd.pc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ldd
 * @Date 2024/4/10
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

//等待 业务 通知
class Data2{//数字 资源类
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0){
                //等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+">=现在还有--->"+num);
            //通知其他线程,我加一完毕了
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0){
                //等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+">=现在还有--->"+num);
            //通知其他线程,我加一完毕了
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}