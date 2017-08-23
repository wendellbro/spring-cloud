package com.pengwu.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 服务消费端
 */
@SpringBootApplication
@EnableDiscoveryClient	//@EnableDiscoveryClient注解，能激活Eureka中的DiscoveryClient实现，这样才能实现Controller中对服务信息的输出。
public class EurekaConsumerRibbonApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerRibbonApplication.class)
        		.web(true).run(args);
    }
    
    @Bean
    @LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
    
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();//这里配置策略，和配置文件对应
    }
}