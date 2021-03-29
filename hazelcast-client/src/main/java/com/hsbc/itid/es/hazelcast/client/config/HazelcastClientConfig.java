package com.hsbc.itid.es.hazelcast.client.config;

import com.hazelcast.client.config.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastClientConfig {

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaUrl;

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("eureka");
        clientConfig.getNetworkConfig().getEurekaConfig().setEnabled(true)
                .setProperty("namespace", "hazelcast")
                .setProperty("use-metadata-for-host-and-port", "true")
                .setProperty("use-classpath-eureka-client-props","false")
                .setProperty("name","hazelcast-server")
                .setProperty("serviceUrl.default",eurekaUrl);
        return clientConfig;
    }

}
