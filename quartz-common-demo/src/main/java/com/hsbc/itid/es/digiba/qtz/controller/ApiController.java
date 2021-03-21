package com.hsbc.itid.es.digiba.qtz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hsbc.itid.es.digiba.qtz.entity.Business;
import com.hsbc.itid.es.digiba.qtz.service.BusinessService;
import com.hsbc.itid.es.digiba.quartz.entity.Result;
import com.hsbc.itid.es.log.annotation.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {


    @Autowired
    private BusinessService businessService;

    @PostMapping("/cron/count")
    public Result count(Business business) throws Exception {
        return businessService.releaseByCorn(business);
    }

    @PostMapping("/release")
    public boolean release(Business business) throws JsonProcessingException {
        return businessService.release(business);
    }

    @PostMapping("/release/custom")
    //@LogAnnotation(module = "quartz-demo-service")
    public Result releaseCustom(Business business) throws Exception {
        return businessService.releaseByCustomTime(business);
    }

}
