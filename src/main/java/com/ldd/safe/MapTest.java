package com.ldd.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author ldd
 * @Date 2024/4/11
 */
public class MapTest {
    public static void main(String[] args) {
        //Map<Integer , String> map = new HashMap<>();
        Map<Integer , String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 70; i++) {
            Integer count=i;
            new Thread(()->{
                map.put(count,UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
