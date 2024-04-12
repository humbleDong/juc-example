package com.ldd.safe;


import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author ldd
 * @Date 2024/4/11
 */
public class SetTest {
    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
