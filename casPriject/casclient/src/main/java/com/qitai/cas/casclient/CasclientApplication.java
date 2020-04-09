package com.qitai.cas.casclient;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableCasClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.qitai.cas.controller"})
public class CasclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasclientApplication.class, args);
    }

}
