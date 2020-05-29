package com.qitai.controller;

import com.qitai.entity.User;
import com.qitai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class StartController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/get")
    @ResponseBody
    public User getUser(){
        return userService.findById(1);
    }
    @RequestMapping("/user/getlist")
    @ResponseBody
    public String getUserList(){
        return userService.getUserList().toString();
    }

    @RequestMapping("/admin/get")
    @ResponseBody
    public String getAdmin(){
        return "管理员权限接口";
    }
}
