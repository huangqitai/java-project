package com.qitai.eurekafeignclientcopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients     //这是新增的注解 表示开启feign
@EnableEurekaClient
public class EurekaFeignClientCopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignClientCopyApplication.class, args);
    }

}
