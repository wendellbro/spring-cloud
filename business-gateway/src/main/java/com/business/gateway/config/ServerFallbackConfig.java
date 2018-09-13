package com.business.gateway.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.MediaType;  
import org.springframework.http.client.ClientHttpResponse;  

import com.alibaba.fastjson.JSON;
import com.business.gateway.model.CommonResponse;  

/**
 * @describe 请求失败时的回滚处理  hystrix的回滚能力 
 * @author wupeng
 * @createtime 2017年9月12日
 */
@Configuration
public class ServerFallbackConfig implements ZuulFallbackProvider {

	@Override
	public String getRoute() {
		
		return "*";	//API服务ID，如果需要所有调用都支持回退，则return "*" 或 return null
	}

	@Override
	public ClientHttpResponse fallbackResponse() {

		return new ClientHttpResponse(){  

			@Override  
			public InputStream getBody() throws IOException {  
				CommonResponse response = new CommonResponse();
				response.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
				response.setMsg(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
				return new ByteArrayInputStream(JSON.toJSONString(response).getBytes(StandardCharsets.UTF_8));  
			}  

			@Override  
			public HttpHeaders getHeaders() {  
				HttpHeaders headers = new HttpHeaders();  
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);  //和body中的内容编码一致，否则容易乱码  
				return headers;  
			}  

			/** 
			 * 网关向API服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 返回服务不可用即可
			 * 不应该把API的404,500等问题抛给客户端，网关和API服务集群对于客户端来说是黑盒子 
			 */  
			@Override  
			public HttpStatus getStatusCode() throws IOException {  

				return HttpStatus.SERVICE_UNAVAILABLE;  
			}  

			@Override  
			public int getRawStatusCode() throws IOException {  

				return HttpStatus.SERVICE_UNAVAILABLE.value();  
			}  

			@Override  
			public String getStatusText() throws IOException {  

				return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();  
			}  

			@Override  
			public void close() {  

			}  

		};  
	}

}
