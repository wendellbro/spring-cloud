package com.pengwu.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pengwu.consumer.rsclient.DsClient;

@Service
public class DsService {
	
	@Autowired
	private DsClient dsClient;
	
	@HystrixCommand(fallbackMethod = "fallback", commandProperties={
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="20000")
			})
    public String dc() {
        return dsClient.dc();
    }
	
	public String fallback(Throwable e) {  
        System.out.println("execute okFallback!" + e.getMessage());
        return "fallback";
    }
}
