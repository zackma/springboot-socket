package com.example.demo.controller;

import com.example.demo.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@Controller
@RequestMapping(value = "/socket")
public class SocketIOController {

    @Autowired
    private SocketIOService socketIOService;

    @RequestMapping(value = "/socketDemo")
    public String startSocket(){
        try {
            socketIOService.startServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "socketio/socketdemo";
    }
}
