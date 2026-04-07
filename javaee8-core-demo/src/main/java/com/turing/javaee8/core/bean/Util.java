package com.turing.javaee8.core.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {
	public String toUpper(String message)
	{
		return message.toUpperCase();
	}
	public void destroy()
	{
		log.info("Util destroyed");
	}
}
