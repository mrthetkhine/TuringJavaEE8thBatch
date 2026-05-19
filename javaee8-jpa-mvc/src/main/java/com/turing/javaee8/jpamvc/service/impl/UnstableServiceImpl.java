package com.turing.javaee8.jpamvc.service.impl;

import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.service.UnstableService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UnstableServiceImpl implements UnstableService{

	int count = 0;
	
	@Retryable(
	        includes = RuntimeException.class,//Beaware of this
	        maxRetries = 3,
	        delay = 100,
	        jitter = 10,
	        multiplier = 2,
	        maxDelay = 1000)
	@Override
	public String getData() {
		
		count ++;
		log.info("Count "+count);
		if(count %3 ==0)
		{
			return "Hello";
		}
		throw new RuntimeException("Network unavailable");
	}

}
