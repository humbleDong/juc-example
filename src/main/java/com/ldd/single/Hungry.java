package com.ldd.single;

/**
 * @Author ldd
 * @Date 2024/4/12
 * 饿汉式的单例模式
 * 主要是让构造器私有，但是容易造成这个类不使用就会浪费内存空间
 */

public class Hungry {
    //一上来就创建对象，可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];
    private Hungry() {
    }
    private final static Hungry HUNGRY= new Hungry();
    public static Hungry getInstance() {
        return HUNGRY;
    }
}
