package com.hsbc.itid.es.provider.controller;

import com.hsbc.itid.es.provider.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
public class ServiceProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/provider/{id}")
    public R providerInfo(@PathVariable String id){
        log.info("Provider port: {}, request id: {}", port,id);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("1","hello");
        hashMap.put("2","world");
        hashMap.put("port",port);
        hashMap.put("time", LocalDateTime.now().toString());
        return R.ok(hashMap);
    }

}
