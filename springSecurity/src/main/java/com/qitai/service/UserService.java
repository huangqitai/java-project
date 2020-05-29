package com.qitai.service;

import com.qitai.ServiceInterface.IUserService;
import com.qitai.dao.UserDao;
import com.qitai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findBySso(String name) {

        return null;
    }

    public List getUserList(){
        List list = jdbcTemplate.queryForList("select * from security_user");
        System.out.println(list.toString());
        return list;
    }
}
