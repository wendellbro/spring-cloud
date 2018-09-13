package com.business.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.business.gateway.filter.ErrorFilter;
import com.business.gateway.filter.LoggerFilter;
import com.business.gateway.filter.SessionFilter;

/**
 * @describe Zuul配置
 * @author wupeng
 * @createtime 2017年9月6日
 */
@Configuration
public class ZuulFilterConfig {

	@Bean
	public SessionFilter sessionFilter() {
		return new SessionFilter();		//会话过滤器
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();		//统一异常过滤器
	}
	@Bean
	public LoggerFilter loggerFilter() {
		return new LoggerFilter();		//访问日志过滤器
	}

}
