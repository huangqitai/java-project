package com.qitai.cas.casclienttwo;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableCasClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.qitai.cas.controller"})
public class CasclientTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasclientTwoApplication.class, args);
    }

}
