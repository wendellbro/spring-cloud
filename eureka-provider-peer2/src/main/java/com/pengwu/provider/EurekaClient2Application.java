package com.pengwu.provider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 创建提供服务的客户端，并向服务注册中心注册自己。
 * 在服务提供方中尝试着提供一个接口来获取当前所有的服务信息。
 */
@SpringBootApplication
@EnableDiscoveryClient	//@EnableDiscoveryClient注解，能激活Eureka中的DiscoveryClient实现，这样才能实现Controller中对服务信息的输出。
public class EurekaClient2Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaClient2Application.class)
        		.web(true).run(args);
    }
}