package com.ldd.pc.syn;

/**
 * @Author ldd
 * @Date 2024/4/10
 * 线程间的通信问题：生产者和消费者的问题！等待唤醒和通知唤醒
 * 线程交替执行 A B同时操作一个变量
 * A num+1
 * B num-1
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        //测试虚假唤醒
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                try {
//                    data.increment();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"C").start();
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                try {
//                    data.decrement();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"D").start();
    }
}

//等待 业务 通知
class Data{//数字 资源类
    private int num = 0;
    public synchronized void increment() throws InterruptedException {
//        if (num != 0){
//            //等待
//            this.wait();
//        }
        //防止虚假唤醒
        while (num != 0){
            //等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+">=现在还有--->"+num);
        //通知其他线程,我加一完毕了
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
//        if (num == 0){
//            this.wait();
//        }
        while (num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+">=现在还有--->"+num);
        //通知其他线程,我减一完毕
        this.notifyAll();
    }

}