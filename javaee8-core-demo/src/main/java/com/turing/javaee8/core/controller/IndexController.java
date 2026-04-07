package com.turing.javaee8.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.core.bean.Util;
import com.turing.javaee8.core.service.GreetingService;
import com.turing.javaee8.core.service.impl.GreetingServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IndexController {
	
	@Autowired
	GreetingService greetingService;
	
	@Autowired
	Util util;
	
	public IndexController()
	{
		log.info("Index Controller created");
	}
	
	@GetMapping("/")
	String index()
	{
		return util.toUpper( this.greetingService.greet());
	}
}
