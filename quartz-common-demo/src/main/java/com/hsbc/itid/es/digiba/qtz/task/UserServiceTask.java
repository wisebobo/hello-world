package com.hsbc.itid.es.digiba.qtz.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.itid.es.digiba.qtz.entity.Business;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userTask")
@Slf4j
public class UserServiceTask {

    @Autowired
    private ObjectMapper objectMapper;

    public void release(String params) throws JsonProcessingException {
        Business business = objectMapper.readValue(params,Business.class);
        log.info("Business: {} ",business);
    }

}
