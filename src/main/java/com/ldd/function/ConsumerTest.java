package com.ldd.function;

import java.util.function.Consumer;

/**
 * @Author ldd
 * @Date 2024/4/12
 * 消费者接口
 */
public class ConsumerTest {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer=(s)->{System.out.println(s);};
        consumer.accept("今天天气好！");
    }
}
