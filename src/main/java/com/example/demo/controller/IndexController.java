package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Map<String, Object> model){
        model.put("time", new Date());
        model.put("message", "这是测试的内容。。。");
        model.put("toUserName", "张三");
        model.put("fromUserName", "老许");
        return "index";
    }

}
