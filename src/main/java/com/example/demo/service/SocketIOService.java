package com.example.demo.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.example.demo.entity.ChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@Service("socketIOService")
public class SocketIOService {


    static SocketIOServer server;

    public void startServer() throws InterruptedException {
        Configuration config = new Configuration();

        config.setHostname("localhost");
        config.setPort(9092);
        config.setOrigin(null);         //设置为null时允许的跨域为“*”（官方坑）

        server = new SocketIOServer(config);

        //服务添加目标监听然后向目标操作
        server.addEventListener("chatevent", ChatEntity.class, new DataListener<ChatEntity>() {
            public void onData(SocketIOClient socketIOClient, ChatEntity chatEntity, AckRequest ackRequest) throws Exception {
                Runnable runnable = () -> {
                        socketIOClient.sendEvent("chatevent",chatEntity);
                };
                ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
                service.scheduleAtFixedRate(runnable,0,5, TimeUnit.SECONDS);
            }
        });

        server.start();
    }
}
