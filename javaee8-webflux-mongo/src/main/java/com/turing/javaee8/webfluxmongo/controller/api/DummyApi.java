package com.turing.javaee8.webfluxmongo.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping("/api/dummy")
public class DummyApi {
	
	Mono<String> getData()
	{
		return Mono.fromCallable(()->{
			try
			{
				Thread.sleep(3000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			log.info("Thread Name "+ Thread.currentThread().getName());
			return "Helllo";
		}).subscribeOn(Schedulers.boundedElastic());
		
	}
	@GetMapping
	Mono<String> getDummy()
	{
		
		return this.getData();
	}
}
