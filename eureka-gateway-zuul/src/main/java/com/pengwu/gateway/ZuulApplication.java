package com.pengwu.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.pengwu.gateway.filter.AccessFilter;

/**
 * 创建提供服务的客户端，并向服务注册中心注册自己。
 * 在服务提供方中尝试着提供一个接口来获取当前所有的服务信息。
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class)
        		.web(true).run(args);
    }
    
    @Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}