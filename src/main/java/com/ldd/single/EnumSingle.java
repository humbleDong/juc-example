package com.ldd.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author ldd
 * @Date 2024/4/12
 */
public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance() {
        return INSTANCE;
    }
}
class Test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingle enumSingle1 = EnumSingle.INSTANCE;
        //反射不能破坏枚举
//        EnumSingle enumSingle2 = EnumSingle.INSTANCE;

        //如果这是简单去看.class文件是会发现有一个无参构造
        //Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);

        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        //Cannot reflectively create enum objects
//	at java.lang.reflect.Constructor.newInstance(Constructor.java:417)
        declaredConstructor.setAccessible(true);
        EnumSingle enumSingle2 = declaredConstructor.newInstance();
        System.out.println(enumSingle1);
        System.out.println(enumSingle2);

    }
}




