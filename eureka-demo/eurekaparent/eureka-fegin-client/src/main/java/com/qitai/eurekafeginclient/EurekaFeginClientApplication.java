package com.qitai.eurekafeginclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients     //这是新增的注解 表示开启feign
@EnableEurekaClient
public class EurekaFeginClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeginClientApplication.class, args);
    }

}
