package com.turing.javaee8.webfluxmongo.operator;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class AddPreselectTest {
	@Test
	public void testPreSelect()
	{
		Flux<Integer> items = Flux.just(1,2,3);
		//Flux<Integer> another = items.startWith(100,200);
		Flux<Integer> another = items.concatWithValues(100,200);
		another.subscribe(System.out::println);
	}
}
