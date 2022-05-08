package com.iblue.esky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h1>用户账户微服务启动入口</h1>
 * 127.0.0.1:8003/ecommerce-account-service/swagger-ui.html
 * 127.0.0.1:8003/ecommerce-account-service/doc.html
 * */
@SpringBootApplication
@EnableDiscoveryClient
//@Import(DataSourceProxyAutoConfiguration.class)
public class AccountApplication {

    public static void main(String[] args) {

        SpringApplication.run(AccountApplication.class, args);
    }
}