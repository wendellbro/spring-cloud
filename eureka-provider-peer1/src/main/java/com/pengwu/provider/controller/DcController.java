package com.pengwu.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@GetMapping("/dc")
	public String dc() throws InterruptedException {
		Thread.sleep(3000);
		String services = "Services: " + discoveryClient.getServices()+" -- client1";
		System.out.println(services);
		return services;
	}
}