package com.turing.javaee8.jpamvc.service.impl;

import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{

	@Override
	public String greet() {
		// TODO Auto-generated method stub
		return "Hello from service";
	}

}
