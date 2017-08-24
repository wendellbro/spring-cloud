package com.pengwu.registry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 创建“服务注册中心”使用Spring Cloud Eureka实现服务治理。
 */
@EnableEurekaServer
@SpringBootApplication	//通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话。
public class EurekaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerApplication.class)
                    .web(true).run(args);
    }
}