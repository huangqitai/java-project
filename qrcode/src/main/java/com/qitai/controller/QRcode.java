package com.qitai.controller;

import com.qitai.util.CreatQRcode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class QRcode {
    @RequestMapping("/getQRcode")
    public void getQRcode(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        CreatQRcode creatQRcode = new CreatQRcode();
        //creatQRcode.getQRcode();
        creatQRcode.getQRcode(response);
        //response.getOutputStream().write(1024);
        //String string =  response.getOutputStream().toString();
        //response.getWriter().print(string);

        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
