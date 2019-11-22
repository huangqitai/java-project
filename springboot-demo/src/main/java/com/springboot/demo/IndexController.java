package com.springboot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    /**
     * 前端页面控制器
     *
     * @return 页面
     */
    @RequestMapping(value = "/nature/**", produces = "text/html;charset=UTF-8")
    public ModelAndView home() {

        return new ModelAndView("index");
    }

}
