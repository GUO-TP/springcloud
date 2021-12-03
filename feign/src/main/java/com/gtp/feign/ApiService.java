package com.gtp.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eurekaclient")
public interface ApiService {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    String index();

}