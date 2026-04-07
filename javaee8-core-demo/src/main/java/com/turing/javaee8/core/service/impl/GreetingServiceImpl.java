package com.turing.javaee8.core.service.impl;

import org.springframework.stereotype.Service;

import com.turing.javaee8.core.service.GreetingService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GreetingServiceImpl implements GreetingService{

	public GreetingServiceImpl()
	{
		log.info("GreetingServiceImpl created");
	}
	@PostConstruct
	public void init()
	{
		log.info("GreetingServiceImpl intialized");
	}
	@Override
	public String greet() {
		return "Hello from service";
	}
	
	@PreDestroy
	public void destroy()
	{
		log.info("GreetingServiceImpl PreDestroy ");
	}

}
