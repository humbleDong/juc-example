package com.ldd.aid;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ldd
 * @Date 2024/4/11
 * 减法计数器
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //等待计数器归零才会向下执行
        countDownLatch.await();
        countDownLatch.countDown();//-1
    }
}
