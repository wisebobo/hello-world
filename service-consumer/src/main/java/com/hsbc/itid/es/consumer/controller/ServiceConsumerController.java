package com.hsbc.itid.es.consumer.controller;

import com.hsbc.itid.es.consumer.feign.ProviderFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ServiceConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ProviderFeignClient providerFeignClient;

    @GetMapping("/consumer/restTemplate/{id}")
    public String restTemplateInfo(@PathVariable String id){
        //SERVICE-PROVIDER: your provider name
        ResponseEntity<String> user = restTemplate.getForEntity("http://SERVICE-PROVIDER/provider/{1}", String.class, id);
        return user.getBody();
    }

    @GetMapping("/consumer/openfeign/{id}")
    public String userInfo(@PathVariable String id){
        return providerFeignClient.providerInfo(id);
    }



}
