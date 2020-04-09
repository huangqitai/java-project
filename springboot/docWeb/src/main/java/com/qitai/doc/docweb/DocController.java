package com.qitai.doc.docweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DocController {
    @RequestMapping("/redirect")
    public void requestDoc(HttpServletResponse response){
        try {
            response.sendRedirect("http://192.168.10.103:3000/#/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/forward")
    public void forwardRequest(HttpServletRequest request,HttpServletResponse response){
        try {
            request.getRequestDispatcher("http://192.168.10.103:3000/#/").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
