package com.pengwu.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pengwu.consumer.rsclient.DsClient;

@Service
public class DsService {
	
	@Autowired
	private DsClient dsClient;
	
	@HystrixCommand(fallbackMethod = "fallback")
    public String dc() {
        return dsClient.dc();
    }
	
	public String fallback() {
        return "fallback";
    }
}
