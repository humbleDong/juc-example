package com.ldd.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author ldd
 * @Date 2024/4/12
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    //临界值，超过这个值就采取ForkJoin
    private Long temp=10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //执行方法
    @Override
    protected Long compute() {
        if ((end-start)<temp){
            long sum=0L;
            for (Long i = start; i <=end ; i++) {
                sum+=i;
            }
            return sum;
        }else {
            long mid=(start+end)/2;//中间值
            ForkJoinTest task1 = new ForkJoinTest(start, mid);
            task1.fork();//拆分任务，把任务压入线程队列
            ForkJoinTest task2 = new ForkJoinTest(mid + 1, end);
            task2.fork();

            return task1.join()+task2.join();
        }
    }
}
