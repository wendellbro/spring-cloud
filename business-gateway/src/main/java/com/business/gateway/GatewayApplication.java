package com.business.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.business.gateway.config.RibbonConfig;

/**
 * 启动服务，加载zuul相关配置
 */
@SpringBootApplication
@RibbonClients(defaultConfiguration=RibbonConfig.class)
@EnableFeignClients(basePackages="com.business.gateway.remote")
@EnableZuulProxy
public class GatewayApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);
	
    public static void main(String[] args) {
    	String spring_config_location = System.getProperty("spring.config.location");
    	LOGGER.info("==================spring.config.location = "+spring_config_location+"==================");
        new SpringApplicationBuilder(GatewayApplication.class)
        		.web(true).run(args);
    }
}