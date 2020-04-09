package com.qitai.cas.controller;

import org.apache.catalina.session.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cas")
public class CasController {

    //获取properties文件中单点登录的地址
    @Value("${cas.server-login-url}")
    private String cas_server_login_url; //单点登录的地址

    //获取properties文件中单点登出的地址
    @Value("${cas-server-logout-url}")
    private String cas_server_logout_url; //单点登录的登出地址

    /*//获取properties文件中本系统的登陆地址
    @Value("${app-login-url}")
    private String app_login_url; //本系统的登陆地址
*/
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "cas client success";
    }

    @RequestMapping("/manager/loginOut")
    public void loginOut(HttpSession session, HttpServletRequest request,
                         HttpServletResponse response, ModelMap modelMap) throws Exception {
        session.removeAttribute("musersession");
        session.removeAttribute("_const_cas_assertion_"); //单点登录的session值去除

        session.invalidate();

//单点登录登出
        String logout_url = cas_server_logout_url;
        response.sendRedirect(logout_url);

//return new ModelAndView("/pages/login.jsp"); //spring-boot会自动去src/main/webapp/pages下面找home.html这个文件
    }
}
