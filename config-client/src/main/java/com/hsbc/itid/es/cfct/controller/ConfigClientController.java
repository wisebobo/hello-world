package com.hsbc.itid.es.cfct.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${data.env}")
    private String env;

    @Value("${data.password}")
    private String password;

    @GetMapping("/data")
    public String data(){
        return "env: "+ env+ " password:" + password;
    }


}
