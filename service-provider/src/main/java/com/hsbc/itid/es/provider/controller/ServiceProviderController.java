package com.hsbc.itid.es.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class ServiceProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/provider/{id}")
    public String providerInfo(@PathVariable String id){
        log.info("Provider port: {}", port);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("1","hello");
        hashMap.put("2","world");
        return hashMap.get(id) + port;
    }

}
