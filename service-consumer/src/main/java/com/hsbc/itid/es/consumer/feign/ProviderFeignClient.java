package com.hsbc.itid.es.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "SERVICE-PROVIDER") // your provider name
public interface ProviderFeignClient {

    @GetMapping(value = "/provider/{id}")
    String providerInfo(@PathVariable("id") String id);


}
