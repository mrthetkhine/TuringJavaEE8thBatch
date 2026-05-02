package com.turing.javaee8.jpamvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.jpamvc.service.GreetingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="api")
public class HelloController {

	@Autowired
	GreetingService greetingService;
	
	
	@GetMapping(value="/hello")
	String hello()
	{
		log.info("Inside Hello Controller /hello");
		return this.greetingService.greet();
	}
	@GetMapping(value="/hi")
	String hi()
	{
		log.info("Inside Hello Controller /hi");
		throw new RuntimeException("Something got wrong");
		//return "hi";
	}
}
