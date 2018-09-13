package com.business.gateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;

@Configuration
public class RibbonConfig {

    @Bean  
    public IPing ribbonPing() {  
        return new CustomPingUrl();  
    }  
    
    @Bean
    @ConditionalOnExpression
    public IRule robinRule(){
    	return new CustomRoundRobinRule();		//这里配置负载均衡策略
    }
    
    @Bean
    @LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();		//配置RestTemplate支持负载均衡
	}
    
}
