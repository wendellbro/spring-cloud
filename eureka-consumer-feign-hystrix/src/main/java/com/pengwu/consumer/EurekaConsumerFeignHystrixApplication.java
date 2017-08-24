package com.pengwu.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 服务消费端
 */
@SpringBootApplication
@EnableDiscoveryClient	//@EnableDiscoveryClient注解，能激活Eureka中的DiscoveryClient实现，这样才能实现Controller中对服务信息的输出。
@EnableFeignClients			//开启Feign客户端
@EnableCircuitBreaker		//开启断路器
public class EurekaConsumerFeignHystrixApplication {
	
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerFeignHystrixApplication.class)
        		.web(true).run(args);
    }
    
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();	//这里配置策略，和配置文件对应
    }
    
}