package com.iblue.esky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h1>Nacos Client 工程启动入口</h1>
 * */
@ServletComponentScan
//@EnableCircuitBreaker   // 启动 Hystrix
//@EnableFeignClients
//@RefreshScope   // 刷新配置
@EnableDiscoveryClient
@SpringBootApplication
public class NacosClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(NacosClientApplication.class, args);
    }
}
