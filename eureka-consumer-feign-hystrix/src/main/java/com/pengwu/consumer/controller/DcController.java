package com.pengwu.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pengwu.consumer.service.DsService;

@RestController
public class DcController {

	@Autowired
	private DsService dsService;
	
	@GetMapping("/consumer")
    public String dc() {
        return dsService.dc();
    }
}