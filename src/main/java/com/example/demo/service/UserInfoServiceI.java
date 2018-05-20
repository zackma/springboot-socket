package com.example.demo.service;

import com.example.demo.entity.UserInfo;

public interface UserInfoServiceI {
    public UserInfo findByUsername(String username);
}
