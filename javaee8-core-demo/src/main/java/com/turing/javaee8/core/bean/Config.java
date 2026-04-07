package com.turing.javaee8.core.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Config {

	@Lazy
	@Bean(destroyMethod = "destroy")
	Util createUtil()
	{
		log.info("Util created");
		return new Util();
	}
}
