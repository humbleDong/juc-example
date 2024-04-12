package com.ldd.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author ldd
 * @Date 2024/4/12
 */
public class LazyMan {
    private static boolean flag = false;
    //单线程下确实单例ok
    private volatile static LazyMan lazyMan;
    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + "ok");
        synchronized (LazyMan.class) {
//            if (lazyMan != null) {
//                throw new RuntimeException("不要试图用反射破坏异常");
        }
        //测试3新增变量
        if (flag != false) {
            flag = true;
        } else {
            throw new RuntimeException("不要试图用反射破坏异常");
        }
    }
    //测试1：双重检测锁模式 懒汉式单例模式 DCL懒汉式
    public static LazyMan getInstance(){
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();//不是原子性操作，
                    //1.分配内存空间
                    //2.执行构造方法，初始化对象
                    //3.把这个对象指向这个空间
                    //真实步骤可能执行132.此时lazyman还没被完成构造
                }
            }
        }
        return lazyMan;
    }
    //测试1
//    //多线程并发  未使用双重检测之前，会导致实例被多次创建
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
//    }
    //测试2
//    // 尝试反射破解使其不安全,破坏单例
//    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        LazyMan instance = LazyMan.getInstance();
//        //获得无参构造器
//        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
//        declaredConstructor.setAccessible(true);
//        LazyMan lazyMan = declaredConstructor.newInstance();
//        //单例模式.LazyMan@15fbaa4
//        //单例模式.LazyMan@1ee12a7
//        System.out.println(instance);
//        System.out.println(lazyMan);
//    }

    //  加入一个变量 两个对象都使用反射再次破坏单例模式
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //通过反射破坏标志位qinjiang
        Field flag = LazyMan.class.getDeclaredField("flag");
        flag.setAccessible(true);

        //获得无参构造器
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan lazyMan = declaredConstructor.newInstance();

        flag.set(lazyMan,false);
        LazyMan instance = declaredConstructor.newInstance();
        //单例模式.LazyMan@15fbaa4
        //单例模式.LazyMan@1ee12a7
        System.out.println(instance);
        System.out.println(lazyMan);
    }
}
