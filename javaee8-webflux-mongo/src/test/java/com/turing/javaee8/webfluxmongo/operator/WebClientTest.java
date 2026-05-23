package com.turing.javaee8.webfluxmongo.operator;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class WebClientTest {
	
	WebClient webClient = WebClient.create();
	
	@Test
	public void testApi()
	{
		Mono<String> todo1 = webClient
				   .get()
				   .uri("https://jsonplaceholder.typicode.com/todos/1")
				   .retrieve()
				   .bodyToMono(String.class);
		Mono<String> todo2 = webClient
				   .get()
				   .uri("https://jsonplaceholder.typicode.com/todos/2")
				   .retrieve()
				   .bodyToMono(String.class);
		todo1.subscribe(todo->{
			System.out.println("Todo 1 "+todo);
		});
		todo2.subscribe(todo->{
			System.out.println("Todo 2 "+todo);
		});
		Flux.zip(todo1, todo2)
			.subscribe(tuple->{
				System.out.println("Both complete "+tuple.getT1() +" t2 "+tuple.getT2());
			});
		System.out.println("Done");
		Delay.delay(4000);
	}
}
