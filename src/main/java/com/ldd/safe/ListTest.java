package com.ldd.safe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author ldd
 * @Date 2024/4/11
 * 解决List不安全的方案
 * 1.List<String> list = new Vector<>();
 * 2.List<String> list = Collections.synchronizedList(new ArrayList<>());
 * 3.使用List<String> list = new CopyOnWriteArrayList();
 */
public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1","2","3");
//        list.forEach(System.out::println);

        //并发下ArrayList不安全
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
