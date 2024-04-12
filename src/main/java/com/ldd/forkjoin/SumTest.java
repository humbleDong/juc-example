package com.ldd.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Author ldd
 * @Date 2024/4/12
 * 计算1到10亿的总和
 */
public class SumTest {
    public static void main(String[] args) {
        //test1();//所用：4323
        //test2();//所用：5196，原因是我自己的ForkJoin不够优化
        test3();//185  //快了几十倍
    }

    //方法一:直接for循环没有什么技术含量
    public static void test1(){
        long sum=0;
        long start = System.currentTimeMillis();
        for (long i = 1; i <=100_0000_0000L ; i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+",执行时间："+(end-start));
    }
    //方法二：使用forkjoin
    public static void test2(){
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = pool.submit(task);//提交任务
        Long sum = null;
        try {
            sum = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+",执行时间："+(end-start));
    }
    //方案3：使用并行流
    public static void test3(){
        long start = System.currentTimeMillis();
        long sum= LongStream.range(0L,10_0000_0001L).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+",执行时间："+(end-start));
    }

}
