package com.pengwu.consumer.rsclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eureka-client")	//通知Feign在调用该接口方法时要向Eureka中查询名为eureka-client的服务，从而得到服务URL。
public interface DsClient {

	@RequestMapping(value="/dc", method=RequestMethod.GET)
	public String dc();
}
