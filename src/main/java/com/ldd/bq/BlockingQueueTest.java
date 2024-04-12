package com.ldd.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author ldd
 * @Date 2024/4/11
 * 测试BlockingQueue
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        //test1();
        //test2();
/*        try {
            test3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //抛出异常
    public static void test1() {
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        System.out.println(arrayBlockingQueue.element());//查看队首元素
        //继续添加会出现ava.lang.IllegalStateException异常
        //System.out.println(arrayBlockingQueue.add("d"));
        System.out.println("=============");
        //队列移除顺序
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //继续移除会出现java.util.NoSuchElementException异常
        //System.out.println(arrayBlockingQueue.remove());
    }

    //不抛异常，但是有返回值
    public static void test2() {
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));

        //继续添加返回false
        //System.out.println(arrayBlockingQueue.offer("d"));

        //判断队首
        //System.out.println(arrayBlockingQueue.peek());
        System.out.println("=============");
        //队列移除顺序
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        //继续移除会返回null
        System.out.println(arrayBlockingQueue.poll());
    }

    public static void test3() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        //队列没有位置,一直阻塞
        //arrayBlockingQueue.put("d");
        System.out.println("=============");
        //队列移除顺序
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());//没有这个元素,一直阻塞
    }

    public static void test4() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));

        //等待超过两秒退出
        //arrayBlockingQueue.offer("d", 2, TimeUnit.SECONDS);
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //等待超过两秒九退出
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));

    }
}
