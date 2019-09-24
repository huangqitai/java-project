package com.shirospring.service.impl;

import com.shirospring.dao.UserMapper;

import com.shirospring.pojo.User;
import com.shirospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String getPassword(String name){
        return userMapper.getPassword(name);
    }

    @Override
    public User getUser(String name) {
        return userMapper.getUser(name);
    }
}
