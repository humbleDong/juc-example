package com.ldd.pool;

import java.util.concurrent.*;

/**
 * @Author ldd
 * @Date 2024/4/11
 */
public class OwnThreadPool {
    public static void main(String[] args) {
        ExecutorService threadExecutor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());//默认不处理，抛出异常 RejectedExecutionException
//        new ThreadPoolExecutor.CallerRunsPolicy();//唤醒调用它的线程
//        new ThreadPoolExecutor.DiscardOldestPolicy(); //队列满了，尝试去和最早得竞争，也不会抛出异常
//        new ThreadPoolExecutor.DiscardPolicy();//队列满了不会抛出异常，丢掉任务
        try {
            //最大承载：队列+max值 不断去修改for的次数
            for (int i = 1; i <= 9; i++) {
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
