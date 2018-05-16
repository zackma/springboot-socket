package com.example.demo.controller;

import com.example.demo.entity.ChatEntity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public class TestController {


    public static void main(String... args) {
        //ArrayList demo
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = Arrays.asList(array);
        Optional<Long> count = Optional.of(list.stream().count());
        int sum = 0;
        List<Map<Integer, Integer>> list2 = new ArrayList();
        sum = list.stream().filter(integer -> integer > 4).collect(Collectors.summingInt(Integer::intValue));
        list.stream().forEach(integer -> {
            Map m = new HashMap();
            m.put(integer, integer);
            list2.add(m);
        });

        //entity demo
        ChatEntity chatEntity1 = new ChatEntity("小明", "我来自Java星");
        ChatEntity chatEntity2 = new ChatEntity("小蓝", "我来自Python星");
        ChatEntity chatEntity3 = new ChatEntity("小强", "我来自喵星");
        List<ChatEntity> chatList = new ArrayList();
        chatList.add(chatEntity1);
        chatList.add(chatEntity2);
        chatList.add(chatEntity3);
        chatList = chatList.stream().filter(chat -> chat.getUsername().contains("强")).collect(Collectors.toList());
        chatList.stream().forEach(chat -> System.out.println("username:" + chat.getUsername() + ";message:" + chat.getMessage()));

        ChatEntity chatEntity4 = new ChatEntity();
        Optional op = Optional.ofNullable(chatEntity4.getUsername());
        System.out.println(op.orElse("AAAAA"));

    }

}
