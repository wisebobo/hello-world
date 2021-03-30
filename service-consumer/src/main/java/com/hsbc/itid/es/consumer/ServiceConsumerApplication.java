package com.hsbc.itid.es.consumer;

import com.hsbc.itid.es.log.annotation.EnableLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
@EnableLogging
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class,args);
    }

}
