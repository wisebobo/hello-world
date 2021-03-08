package com.hsbc.itid.es.hazelcast.client;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.eureka.one.EurekaOneDiscoveryStrategyFactory;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class HazelcastClientApplication {


    @Bean
    public ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("eureka");
        clientConfig.getNetworkConfig().getEurekaConfig().setEnabled(true)
                .setProperty("namespace", "hazelcast");
        return clientConfig;
    }

//    @Bean
//    public ClientConfig clientConfig(EurekaClient eurekaClient) {
//        EurekaOneDiscoveryStrategyFactory.setEurekaClient(eurekaClient);
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.setClusterName("eureka");
//        clientConfig.getNetworkConfig().getEurekaConfig().setEnabled(true)
//                .setProperty("namespace", "hazelcast");
//        return clientConfig;
//    }


    public static void main(String[] args) {
        SpringApplication.run(HazelcastClientApplication.class, args);
    }

}
