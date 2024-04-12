package com.ldd.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ldd
 * @Date 2024/4/11
 * //线程池的简单使用
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        //ExecutorService threadExecutor = Executors.newFixedThreadPool(5);
        //ExecutorService threadExecutor = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"--->ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadExecutor.shutdown();//关闭线程池
        }
    }
}
