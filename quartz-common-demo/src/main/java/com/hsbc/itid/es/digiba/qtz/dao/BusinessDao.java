package com.hsbc.itid.es.digiba.qtz.dao;

import com.hsbc.itid.es.digiba.qtz.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessDao {

    @Select("select * from business")
    List<Business> selectAll();


}
