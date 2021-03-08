package com.hsbc.itid.es.hazelcast;

import com.hazelcast.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HazelcastApplication {

    @Value("${hazelcast.port:5701}")
    private int hazelcastPort;

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();
        config.getNetworkConfig().setPort(hazelcastPort);
        config.setClusterName("eureka");
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getEurekaConfig()
                .setEnabled(true)
                .setProperty("self-registration", "true")
                .setProperty("namespace", "hazelcast");

        return config;
    }

//    @Bean
//    public Config hazelcastConfig(EurekaClient eurekaClient) {
//        EurekaOneDiscoveryStrategyFactory.setEurekaClient(eurekaClient);
//        Config config = new Config();
//        config.setClusterName("eureka");
//        config.getNetworkConfig().setPort(hazelcastPort);
//        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
//        config.getNetworkConfig().getJoin().getEurekaConfig()
//                .setEnabled(true)
//                .setProperty("self-registration", "true")
//                .setProperty("namespace", "hazelcast")
//                .setProperty("use-metadata-for-host-and-port", "true");
//        return config;
//    }

    public static void main(String[] args) {
        SpringApplication.run(HazelcastApplication.class, args);
    }

}
