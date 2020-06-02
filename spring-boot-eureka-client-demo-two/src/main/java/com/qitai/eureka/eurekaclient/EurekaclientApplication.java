package com.qitai.eureka.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang_
 * @EnableEurekaClient 表明自己是一个eurekaclient.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaclientApplication {

    public static void main(String[] args) {

        SpringApplication.run(EurekaclientApplication.class, args);
    }

    @Value("${server.port}")
    String port;
   /* @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }*/

    @RequestMapping("/")
    public String home() {
        return "Hello world ,port:" + port;
    }
}
