package com.ldd.hello.syn;

/**
 * @Author ldd
 * @Date 2024/4/10
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //Runnable接口为函数式接口
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"b").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"c").start();
    }
}


class Ticket{
    //定义一个票的数量
    private int number=50;
    private int count=1;

    //卖票的方法
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了第"+(51-number)+",现在还剩"+(--number)+"张票");
        }
    }
}
