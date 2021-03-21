package com.hsbc.itid.es.digiba.qtz.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.itid.es.digiba.qtz.dao.BusinessDao;
import com.hsbc.itid.es.digiba.qtz.entity.Business;
import com.hsbc.itid.es.digiba.qtz.service.BusinessService;
import com.hsbc.itid.es.digiba.quartz.constant.ScheduleConstants;
import com.hsbc.itid.es.digiba.quartz.entity.Result;
import com.hsbc.itid.es.digiba.quartz.entity.SysJob;
import com.hsbc.itid.es.digiba.quartz.service.ISysJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service("businessService")
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    private BusinessDao businessDao;

    private ObjectMapper objectMapper;

    private ISysJobService iSysJobService;

    public BusinessServiceImpl(BusinessDao businessDao,ObjectMapper objectMapper,ISysJobService iSysJobService){
        this.businessDao = businessDao;
        this.iSysJobService = iSysJobService;
        this.objectMapper = objectMapper;
    }

    @Autowired
    private Scheduler scheduler;
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
    }


    @Value("${spring.application.name}")
    private String JOB_GROUP;

    @Override
    public List<Business> selectAll(){
        return businessDao.selectAll();
    }

    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public boolean release(Business business) throws JsonProcessingException {

        //valid
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //doRelease
        return doRelease(objectMapper.writeValueAsString(business));

    }

    @Override
    public Result releaseByCorn(Business business) throws Exception {
        SysJob sysJob = new SysJob();
        sysJob.setJobName("XXX Cron Count Job");
        sysJob.setJobGroup(JOB_GROUP);
        sysJob.setInvokeTarget("businessService.countTotalUser()");
        sysJob.setCronExpression(business.getCornExpression());
        sysJob.setTriggerType(ScheduleConstants.TriggerType.CRON.getValue());
        //sysJob.setParams(objectMapper.writeValueAsString(business));
        Result resultInsert = iSysJobService.insertJob(sysJob);
        log.info("corn job create result: {}",resultInsert);
        return resultInsert;
    }

    @Override
    public Result releaseByCustomTime(Business business) throws Exception  {
        SysJob sysJob = new SysJob();
        sysJob.setJobName("XXX Release Job");
        sysJob.setJobGroup(JOB_GROUP);
        sysJob.setInvokeTarget("businessService.doRelease()");
        sysJob.setStartTime(business.getReleaseTime());
        sysJob.setTriggerType(ScheduleConstants.TriggerType.SIMPLE.getValue());
        sysJob.setParams(objectMapper.writeValueAsString(business));
        Result resultInsert = iSysJobService.insertJob(sysJob);
        log.info("corn job create result: {}",resultInsert);
        return resultInsert;
    }

    public void countTotalUser() throws JsonProcessingException {
        int count = atomicInteger.incrementAndGet();
        log.info("count: {} ",count);
    }


    public boolean doRelease(String params) throws JsonProcessingException {
        Business business = objectMapper.readValue(params,Business.class);
        log.info("Business: {} ",business);
        return true;
    }

}
