package com.hsbc.itid.es.consumer.feign.factory;

import com.hsbc.itid.es.consumer.domain.R;
import com.hsbc.itid.es.consumer.feign.ProviderFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SentinelFeignFactory implements FallbackFactory<ProviderFeignService> {
    @Override
    public ProviderFeignService create(Throwable throwable) {
        log.error("feign request failed:{}", throwable.getMessage());
        return new ProviderFeignService()
        {
            @Override
            public R providerInfo(String id) {
                return R.fail("sentinel fallback");
            }
        };
    }
}
