package com.hsbc.itid.es.digiba.qtz.service.impl;

import com.hsbc.itid.es.digiba.qtz.dao.BusinessDao;
import com.hsbc.itid.es.digiba.qtz.entity.Business;
import com.hsbc.itid.es.digiba.qtz.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    public List<Business> selectAll(){
        return businessDao.selectAll();
    }

}
