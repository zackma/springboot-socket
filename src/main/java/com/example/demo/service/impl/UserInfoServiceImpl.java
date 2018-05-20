package com.example.demo.service.impl;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.UserInfoServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoServiceI {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoRepository.findByUsername(username);
    }
}
