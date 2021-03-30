package com.hsbc.itid.es.consumer.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.ctc.wstx.util.ExceptionUtil;
import com.hsbc.itid.es.consumer.handler.SentinelExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

//    @Bean
//    @LoadBalanced
//    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = SentinelExceptionUtil.class, fallback = "fallback", fallbackClass = SentinelExceptionUtil.class)
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
//        return restTemplate;
//    }

}
