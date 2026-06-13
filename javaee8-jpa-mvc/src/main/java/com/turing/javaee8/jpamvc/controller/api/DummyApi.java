package com.turing.javaee8.jpamvc.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/dummy")
public class DummyApi {
	
	@GetMapping
	String getDummy()
	{
		log.info("Thread Name "+ Thread.currentThread().getName());
		try
		{
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Helllo";
	}
}
