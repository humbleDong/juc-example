package com.ldd.function;

import java.util.function.Predicate;

/**
 * @Author ldd
 * @Date 2024/4/12
 * 断定型接口
 */
public class PredicateTest {
    public static void main(String[] args) {
        //判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
        Predicate<String> predicate = (str)->{return str.isEmpty();};
        System.out.println(predicate.test(""));
    }
}
