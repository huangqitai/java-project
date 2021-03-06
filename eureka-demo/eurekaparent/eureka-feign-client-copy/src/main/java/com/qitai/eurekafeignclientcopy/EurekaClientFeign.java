package com.qitai.eurekafeignclientcopy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component   //注入到spring 中 否则 无法注入的
@FeignClient(value = "eureka-feign-client") //value表示调用的服务名
public interface EurekaClientFeign {

    @GetMapping("/sout")    //客户端的方法地址
    String getMessageFromClient();
}
