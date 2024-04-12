package com.ldd.function;

import java.util.function.Function;

/**
 * @Author ldd
 * @Date 2024/4/12
 * 函数型接口
 */
public class FunctionTest {
    public static void main(String[] args) {
//        Function function = new Function<String,String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function function = (str)->{return str;};
        System.out.println(function.apply("2024"));
    }
}
