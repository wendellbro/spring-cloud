package com.pengwu.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@GetMapping("/dc")
	public String dc() throws InterruptedException {
		String services = "Services: " + discoveryClient.getServices()+" -- client2";
		System.out.println(services);
		return services;
	}
	
	@GetMapping("/host")
	public String host() throws InterruptedException {
		return "host=peer2";
	}
}