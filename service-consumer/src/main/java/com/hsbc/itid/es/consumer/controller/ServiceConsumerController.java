package com.hsbc.itid.es.consumer.controller;

import com.hsbc.itid.es.consumer.domain.R;
import com.hsbc.itid.es.consumer.feign.ProviderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class ServiceConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ProviderFeignService providerFeignService;

    @GetMapping(value = "/consumer/restTemplate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public R restTemplateInfo(@PathVariable String id){
        //ResponseEntity<String> user = restTemplate.getForEntity("http://127.0.0.1:8085/provider/{1}", String.class, id);
        //SERVICE-PROVIDER: your provider name
        ResponseEntity<R> user = restTemplate.getForEntity("http://SERVICE-PROVIDER/provider/{1}", R.class, id);
        return user.getBody();
    }

    @GetMapping(value = "/consumer/openfeign/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public R userInfo(@PathVariable String id){
        log.info("id: "+id);
        return providerFeignService.providerInfo(id);
    }

    @GetMapping("/consumer/gateway")
    public String gateway(){
        return "SUCCESS";
    }

    @GetMapping("/consumer/slow")
    public String slow() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);

        return "SUCCESS";
    }



}
