package com.qitai.ServiceInterface;

import com.qitai.entity.User;

import java.util.List;

public interface IUserService {

    User findById(int id);

    User findBySso(String name);
    List getUserList();

}
