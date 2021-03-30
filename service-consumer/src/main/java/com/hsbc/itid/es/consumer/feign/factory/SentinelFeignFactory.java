package com.hsbc.itid.es.consumer.feign.factory;

import com.hsbc.itid.es.consumer.domain.R;
import com.hsbc.itid.es.consumer.feign.SentinelFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Component
public class SentinelFeignFactory implements FallbackFactory<SentinelFeignService> {
    @Override
    public SentinelFeignService create(Throwable throwable) {
        log.error("feign request failed:{}", throwable.getMessage());
        return new SentinelFeignService()
        {
            @Override
            public R providerInfo(String id) {
                return R.fail("sentinel fallback");
            }
        };
    }
}
