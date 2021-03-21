package com.hsbc.itid.es.digiba.qtz;

import com.hsbc.itid.es.digiba.quartz.EnableQuartz;
import com.hsbc.itid.es.log.annotation.EnableLogging;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableQuartz
@MapperScan(
        basePackages = {"com.hsbc.itid.es.digiba.quartz.mapper","com.hsbc.itid.es.digiba.qtz.dao"}
)
@EnableLogging
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class,args);
    }

}
