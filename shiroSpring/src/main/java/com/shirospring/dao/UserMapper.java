package com.shirospring.dao;

import com.shirospring.pojo.User;

public interface UserMapper {
    String getPassword(String name);
    User getUser(String name);
}
