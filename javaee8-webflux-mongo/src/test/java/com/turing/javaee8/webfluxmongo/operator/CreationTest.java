package com.turing.javaee8.webfluxmongo.operator;

import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class CreationTest {
	
	//@Test
	public void testSimple()
	{
		
		Flux<Integer> list = Flux.range(1, 10);
		list.subscribe(data->{
			System.out.println("Data "+data);
		});
		System.out.println("Start");
		StepVerifier.create(list)
					.expectNextCount(10)
					.verifyComplete();
		
		Flux<String> letters = Flux.just("A", "B", "C");
		letters.subscribe(data->{
			System.out.println("Letter data "+data);
		});
	    StepVerifier.create(letters)
	    			.expectNext("A", "B", "C")
	    			.verifyComplete();
	    
	    Mono<String> mono = Mono.just("Hello");
	    mono.subscribe(data->{
	    	System.out.println("Data "+data);
	    });
	    
	    Mono<Object> empty = Mono.empty();
	    empty.subscribe(data->{
	    	System.out.println("Empty "+data);
	    });
	    StepVerifier.create(empty).verifyComplete();
	    
	    Flux<Integer> fromArray = Flux.fromArray(new Integer[] { 1, 2, 3 });
	    fromArray.subscribe(data->{
	    	System.out.println("From Array "+data);
	    });
	}
	//@Test
	public void createError()
	{
		Flux<Object> flux = Flux.error(new RuntimeException("Error occured"));
		flux.subscribe(data->{
			System.out.println("Data "+data);
		},err->{
			System.out.println("Error "+err.getMessage());
		});
			
	}
	//@Test
	public void testDefer()
	{
		Flux<String> item = Flux.defer(()->{
			System.out.println("Compute");
			return Flux.just("Something");
		});
		System.out.println("Before");
		item.subscribe(data->{
			System.out.println("Data "+data);
		});
		item.subscribe(data->{
			System.out.println("Data2 "+data);
		});
	}
	@Test
	public void testCallable()
	{
		Mono<String> item = Mono.fromCallable(()->{
			System.out.println("Compute");
			return "Something";
		});
		System.out.println("Before");
		item.subscribe(data->{
			System.out.println("Data "+data);
		});
		item.subscribe(data->{
			System.out.println("Data2 "+data);
		});
	}
}
