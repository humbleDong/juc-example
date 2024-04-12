package com.ldd.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author ldd
 * @Date 2024/4/11
 */
public class CallableTest {
    public static void main(String[] args) {
        //如何启动一个Callable线程
        MyThread thread = new MyThread();
        FutureTask<String> task = new FutureTask<>(thread);//适配器模式
        new Thread(task,"Thread").start();
        //new Thread(task,"Thread2").start();//结果会被缓存,效率高
        //获取到Callable的返回值
        try {
            String s = task.get();//callable的返回值,这个get方法可能会产生阻塞,把他放在最后
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call()方法被执行");
        return "1105";
    }
}
