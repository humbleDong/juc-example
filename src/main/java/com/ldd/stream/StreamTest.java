package com.ldd.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author ldd
 * @Date 2024/4/12
 */
public class StreamTest {
        //需求：1.保留id是偶数的用户
        //需求：2.返回年龄大于20的用户
        //需求：3.将名字大写
        public static void main(String[] args) {
            User user1 = new User(1,21,"zhangsan");
            User user2 = new User(2,23,"lisi");
            User user3 = new User(3,29,"wangwu");
            User user4 = new User(4,18,"zhaoliu");
            //集合存储
            List<User> userList = Arrays.asList(user1, user2, user3, user4);
            //计算交给流：链式编程+流式计算
            userList.stream().filter(user -> {return user.getId()%2==0;})
                    .filter(user -> {return user.getAge()>20;})
                    .map(user -> {return user.getName().toUpperCase();})
//                .limit(1)//分页
                    .forEach(System.out::println);
        }
}
