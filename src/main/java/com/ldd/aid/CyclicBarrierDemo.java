package com.ldd.aid;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author ldd
 * @Date 2024/4/11
 * 加法计数器
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //定义数量，到达指定数量取执行指定线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("集卡成功");
        });
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            //lambda不能直接拿到for循环中的i
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-->收集第" + temp + "张");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"线程"+i).start();
        }
    }
}
