package com.ldd.vola;

import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/12
 * Volatile可见性测试
 */
public class VolatileVisibility {
    //加了volatile可以保证可见性,不加进入死循环
    private volatile static int num = 0;
    public static void main(String[] args) throws InterruptedException {//main线程
        new Thread(()->{//线程1
            while (num == 0) {

            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        num = 1;
        System.out.println(num);
        //程序一直在执行,线程1不知道主存中的值发生了变化
    }
}
