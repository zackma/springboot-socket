package com.example.demo.entity;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
public class ChatEntity {
    private String username;
    private String message;

    public ChatEntity(){}

    public ChatEntity(String username,String message){
        super();
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
