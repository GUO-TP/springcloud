package com.gtp.gateway.filter;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
错误拦截
        在一个大型系统中，服务是部署在不同的服务器下面的，我们难免会遇到某一个服务挂掉或者请求不到的时候，如果不做任何处理，服务网关请求不到会抛出500错误，对用户是不友好的。

        我们为了提供用户的友好性，需要返回友好性提示，zuul 为我们提供了一个名叫 ZuulFallbackProvider 的接口，通过它我们就可以对这些请求不到的服务进行错误处理。
现在开始测试这部分代码，首先停掉服务提供者 eurekaclient，再重启 gateway，请求地址：http://localhost:8080/api/index?token=12345
*/
@Component
public class ApiFallbackProvider implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        return "eurekaclient";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "{code:0,message:\"服务器异常！\"}";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(getStatusText().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
