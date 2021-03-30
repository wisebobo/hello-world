package com.hsbc.itid.es.consumer.feign;

import com.hsbc.itid.es.consumer.domain.R;
import com.hsbc.itid.es.consumer.feign.factory.SentinelFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "SERVICE-PROVIDER-1", fallbackFactory = SentinelFeignFactory.class) // your provider name
public interface SentinelFeignService {

    @GetMapping(value = "/provider/{id}")
    R providerInfo(@PathVariable("id") String id);

}
