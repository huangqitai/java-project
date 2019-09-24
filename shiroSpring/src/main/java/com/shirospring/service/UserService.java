package com.shirospring.service;

import com.shirospring.pojo.User;

public interface UserService {
    String getPassword(String name);
    User getUser(String name);
}
