package com.hsbc.itid.es.consumer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hsbc.itid.es.consumer.domain.R;
import org.springframework.http.HttpStatus;

public class SentinelExceptionUtil {

    public static R defaultBlockHandler(BlockException ex)
    {
        return R.fail(HttpStatus.TOO_MANY_REQUESTS.value(),HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
    }


}
