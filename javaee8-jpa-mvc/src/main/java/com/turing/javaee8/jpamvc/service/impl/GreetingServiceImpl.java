package com.turing.javaee8.jpamvc.service.impl;

import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{

	@Override
	public String greet() {
		// TODO Auto-generated method stub
		try
		{
			//Thread.sleep(10_000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Hello from service";
	}

}
