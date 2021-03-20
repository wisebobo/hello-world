package com.hsbc.itid.es.digiba.qtz.controller;

import com.hsbc.itid.es.digiba.qtz.entity.Business;
import com.hsbc.itid.es.digiba.qtz.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {


    @Autowired
    private BusinessService businessService;

    @GetMapping("/release/data")
    public List<Business> business(){
        return businessService.selectAll();
    }

}
