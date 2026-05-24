package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class AggregateTest {
	//@Test
	void testCollect()
	{
		Flux<Integer> items = Flux.just(1,2,3)
									.delayElements(Duration.ofMillis(100));
		items.collectList()
			 .subscribe(list->{
				 System.out.println("List "+list);
			 });
		Delay.delay(2000);
	}
	//@Test
	void testCollectMap()
	{
		Flux<Integer> items = Flux.just(1,2,3,1)
									.delayElements(Duration.ofMillis(100));
		items.collectMap(item->"Key"+item)
			 .subscribe(map->{
				 System.out.println("List "+map);
			 });
		Delay.delay(2000);
	}
	//@Test
	void testCollectContainer()
	{
		Flux<Integer> items = Flux.just(1,2,3,2,21,1)
									.delayElements(Duration.ofMillis(100));
		/*
		items.collect(Collectors.toSet())
			 .subscribe(list->{
				 System.out.println("List "+list);
			 });
		*/
		items.count()
			.subscribe(count->{
				 System.out.println("Count "+count);
			 });	
		Delay.delay(2000);
	}
	//@Test
	void testReduce()
	{
		Flux<Integer> items = Flux.just(1,2,3,4,5);
		items.reduce((a,b)->{
			System.out.println("A "+a+" b "+b);
			//return a+b;
			return a>b ?a:b;
		})
		.subscribe(data->{
			System.out.println("Result "+data);
		});
		
	}
	@Test
	void testAllOrAny()
	{
		Flux<Integer> items = Flux.just(1,2,3,4,-5);
		//items.all(item->item>0)
		items.any(item->item>0)
		.subscribe(data->{
			System.out.println("Result "+data);
		});
		items.hasElement(3)
		.subscribe(data->{
			System.out.println("hasElement "+data);
		});
	}
}
