package com.qitai.eurekafeginclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @GetMapping("/list")
    public String get(@RequestParam(defaultValue = "sjj", required = false) String name) {
        return feignService.get(name);
    }
    @GetMapping("/sout")
    public String sout(){
        return "输出测试";
    }
}
