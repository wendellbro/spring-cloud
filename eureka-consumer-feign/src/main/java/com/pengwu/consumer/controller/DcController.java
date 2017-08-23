package com.pengwu.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pengwu.consumer.rsclient.DsClient;

@RestController
public class DcController {

	@Autowired
	DsClient dsClient;
	
	@GetMapping("/consumer")
    public String dc() {
        return dsClient.dc();
    }
}