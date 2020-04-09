package com.qitai.eurekadome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekadomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekadomeApplication.class, args);
    }

}
