package com.hsbc.itid.es.digiba.qtz.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.hsbc.itid.es.digiba.qtz.entity.Business;
import com.hsbc.itid.es.digiba.quartz.entity.Result;

import java.util.List;

public interface BusinessService {

    List<Business> selectAll();

    boolean release(Business business) throws JsonProcessingException;

    Result releaseByCorn(Business business) throws Exception;

    Result releaseByCustomTime(Business business) throws Exception;
}
