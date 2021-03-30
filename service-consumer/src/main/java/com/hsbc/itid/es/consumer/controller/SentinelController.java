package com.hsbc.itid.es.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hsbc.itid.es.consumer.domain.R;
import com.hsbc.itid.es.consumer.feign.SentinelFeignService;
import com.hsbc.itid.es.consumer.handler.SentinelExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class SentinelController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private SentinelFeignService sentinelFeignService;

    @SentinelResource(value = "blockHandler", blockHandler = "blockExceptionHandler")
    @GetMapping(value = "/blockHandler", produces = MediaType.APPLICATION_JSON_VALUE)
    public R blockHandler(){
        ResponseEntity<R> user = restTemplate.getForEntity("http://SERVICE-PROVIDER/provider/{1}", R.class, 1);
        return user.getBody();
    }

    public R blockExceptionHandler(BlockException ex)
    {
        return R.fail(HttpStatus.TOO_MANY_REQUESTS.value(),HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
    }

    @SentinelResource(value = "defaultBlockHandler",blockHandlerClass = {SentinelExceptionUtil.class},blockHandler = "defaultBlockHandler")
    @GetMapping(value ="/defaultBlockHandler", produces = MediaType.APPLICATION_JSON_VALUE)
    public R defaultBlockHandler(){
        ResponseEntity<R> user = restTemplate.getForEntity("http://SERVICE-PROVIDER/provider/{1}", R.class, 1);
        return user.getBody();
    }

//    @GetMapping("/restTemple/blockHandler")
//    public String restTempleBlockHandler(){
//        ResponseEntity<String> user = restTemplate.getForEntity("http://SERVICE-PROVIDER/provider/{1}", String.class, 1);
//        return user.getBody();
//    }

    @GetMapping(value = "/openfeign/blockHandler", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<?> openFeignBlockHandler() throws InterruptedException {
        R body = sentinelFeignService.providerInfo("1");
//        TimeUnit.SECONDS.sleep(3);
//        log.info("body-------------",body);
        return body;
    }


}
