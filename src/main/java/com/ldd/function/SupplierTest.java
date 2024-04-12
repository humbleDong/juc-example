package com.ldd.function;

import java.util.function.Supplier;

/**
 * @Author ldd
 * @Date 2024/4/12
 * 供给型接口
 */
public class SupplierTest {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "2024";
//            }
//        };
        Supplier<String> supplier=()->{return "2024";};
        System.out.println(supplier.get());
    }
}
