package com.ldd.aid;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/11
 * 限流
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //acquire()
                try {
                    semaphore.acquire();//获得,如果满了,会等待被释放为止
                    System.out.println(Thread.currentThread().getName()+"进来了");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"出去了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放
                }
            },String.valueOf(i)).start();
        }
    }
}
