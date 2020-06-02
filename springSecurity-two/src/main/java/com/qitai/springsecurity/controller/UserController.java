package com.qitai.springsecurity.controller;

import com.qitai.springsecurity.entity.User;
import com.qitai.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(){
        return userService.getUser("1").toString();
    }

    @RequestMapping("/user/info")
    @ResponseBody
    public String getUserInfo(){
        return userService.getUser("1").toString();
    }

    @RequestMapping("/admin/info")
    @ResponseBody
    public String getAdminInfo(){
        return userService.getUser("2").toString();
    }
}
