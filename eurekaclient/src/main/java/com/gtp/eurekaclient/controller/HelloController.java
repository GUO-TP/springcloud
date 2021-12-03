package com.gtp.eurekaclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
访问注册中心:http://localhost:8761
访问服务提供者：http://localhost:8762/index
访问网关zuul转发：http://localhost:8080/api/index
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("index")
    public String index(){
        return "Hello World!,端口："+port;
    }


}
