package com.ldd.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ldd
 * @Date 2024/4/12
 */
public class CasTest {
        //CAS compareAndSet:比较并交换
        public static void main(String[] args) {
            AtomicInteger atomicInteger = new AtomicInteger(2020);
            //public final boolean compareAndSet(int expect, int update)
            //如果我期望的值达到了就更新,CAS是Cpu的并发原理
//        如果不是就一直循环，底层是自旋锁。
            System.out.println(atomicInteger.compareAndSet(2020, 2021));
            System.out.println(atomicInteger.get());

            System.out.println(atomicInteger.compareAndSet(2020, 2021));
            System.out.println(atomicInteger.get());
//        true
//        2021
//        false
//        2021
        }
}
