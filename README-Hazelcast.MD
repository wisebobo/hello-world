## Hazelcast

Hazelcast is an open-source distributed in-memory data store and computation platform. It provides the leading in-memory computing platform for high-performance data processing at scale, accelerating applications for the most innovative global brands.

### 1.1 Hazelcast Server

Hazelcast server which can store data and need to registered with Eureka Server 

#### 1.1.1 Hazelcast server code introduction.

##### 1.1.11 pom.xml
The following dependency is about hazelcast and hazelcast-eureka
```xml
        <dependency>
            <groupId>com.hazelcast</groupId>
            <artifactId>hazelcast</artifactId>
            <version>4.0</version>
        </dependency>
        <dependency>
            <groupId>com.hazelcast</groupId>
            <artifactId>hazelcast-eureka-one</artifactId>
            <version>2.0.1</version>
        </dependency>
```

The following eureka yml is set up for hazelcast.
```yaml
spring:
  application:
    name: hazelcast-server

eureka:
  instance:
    preferIpAddress: true
    instanceId: ${spring.cloud.client.ip-address}:${hazelcast.port}
    non-secure-port: ${hazelcast.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://Hins:Hins2021@127.0.0.1:1111/eureka,http://Hins:Hins2021@127.0.0.1:1112/eureka

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
```

Below config is about hazelcast server register with eureka.
```java
    @Value("${hazelcast.port:5701}")
    private int hazelcastPort;

    @Bean
    public Config hazelcastConfig(EurekaClient eurekaClient) {
        EurekaOneDiscoveryStrategyFactory.setEurekaClient(eurekaClient);
        Config config = new Config();
        config.setClusterName("eureka");
        config.getNetworkConfig().setPort(hazelcastPort);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getEurekaConfig()
                .setEnabled(true)
                .setProperty("self-registration", "true")
                .setProperty("namespace", "hazelcast")
                .setProperty("use-metadata-for-host-and-port", "true");
        return config;
    }
```

#### 1.1.2 Running Hazelcast server with eureka server.

##### 1.1.21  Running eureka server.
**please find eureka-server README.MD to study how to use it.**

##### 1.1.22 Running Hazelcast server in standalone mode.
Please make sure eureka server is up before you run hazelcast server.

Run HazelcastApplication with 9000 web server port and 5701 hazelcast server port. you can see like below screenshot.
![hazelcast-server](img/hazelcast-server1.PNG)

Go to eureka UI you can see like
![hazelcast-eureka1](img/hazelcast-eureka1.PNG)

##### 1.1.23 Running Hazelcast server in cluster mode.
Base on standalone mode, we can start multiple hazelcast server.

Run HazelcastApplication with 9001 web server port and 5702 hazelcast server port. you can see like below screenshot.
![hazelcast-server2](img/hazelcast-server2.PNG)

Go to eureka UI you can see like
![hazelcast-eureka2](img/hazelcast-eureka2.PNG)


### 1.2 Hazelcast Client

Hazelcast client which is embedded in micro services and it can operate hazelcast server data.

#### 1.2.1 Hazelcast client code introduction.

##### 1.2.11 pom.xml
The following dependency is about hazelcast and hazelcast-eureka
```xml
        <dependency>
            <groupId>com.hazelcast</groupId>
            <artifactId>hazelcast</artifactId>
            <version>4.0</version>
        </dependency>
        <dependency>
            <groupId>com.hazelcast</groupId>
            <artifactId>hazelcast-eureka-one</artifactId>
            <version>2.0.1</version>
        </dependency>
```

Below config is about hazelcast client and it doesn't need to register with eureka.
```java
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
```

Sample code for read and write data in springboot.
```java
package com.hsbc.itid.es.hazelcast.client.controller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    HazelcastController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping(value = "/write-data")
    public String writeDataToHazelcast(@RequestParam String key, @RequestParam String value) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        hazelcastMap.put(key, value);
        return "Data is stored.";
    }

    @GetMapping(value = "/read-data")
    public String readDataFromHazelcast(@RequestParam String key) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastMap.get(key);
    }

    @GetMapping(value = "/read-all-data")
    public Map<String, String> readAllDataFromHazelcast() {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastMap;
    }

}
```


#### 1.2.2 Running Hazelcast client and operate data.

##### 1.2.21 Running Hazelcast client in standalone mode.
Please make sure eureka server and hazelcast server is up before you run hazelcast client.

Run HazelcastClientApplication with 9098 web server port you can see like below screenshot.
![hazelcast-client1](img/hazelcast-client1.PNG)

##### 1.2.22 Read and Write data
a. we send a request to read all data from my-app map. as expected, there're no data in this map.
![read-all-data](img/read-all-data.PNG)

b. we send a request to write-data with key=hello, value=world.
![write-data](img/write-data.PNG)

c. Send a request to read-data with key=hello and send a request to read-all-data, we can get the value we stored.
![read-data](img/read-data.PNG)
![read-all-data2](img/read-all-data2.PNG)

##### 1.2.23 Running Hazelcast client in cluster mode.
Base on standalone mode, we can start multiple hazelcast client.

Run HazelcastClientApplication with 9099 web server port you can see like below screenshot.
![hazelcast-client2](img/hazelcast-client2.PNG)

a. Send a request to read-data with key=hello and send a request to read-all-data, we can get the value which was stored by **port=9098**.
![read-data9099](img/read-data9099.PNG)

b. Stored data from 9099 and read data from 9098.
![write-data9099](img/write-data9099.PNG)

![read-data9098](img/read-data9098.PNG)



