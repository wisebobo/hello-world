package com.hsbc.itid.es.digiba.qtz.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Business {

    private int id;
    private String name;
    private String status;

    private String cornExpression;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
}
