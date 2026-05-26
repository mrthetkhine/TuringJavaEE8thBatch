package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FilterTest {
	//@Test
	public void testFilter()
	{
		Flux<Integer> items = Flux.range(1, 10);
		items.filter(item->item %2==0)
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
	}
	//@Test
	public void testFilterWhen()
	{
		Flux<Integer> items = Flux.range(1, 10);
		items.filterWhen(item->Mono.just( item %2==0)
									.delayElement(Duration.ofMillis(400)))
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
		Delay.delay(4000);
	}
	//@Test
	public void testTypeOf()
	{
		Flux<Object> items = Flux.just("Apple",1,2,"Orange");
		items.ofType(String.class)
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
		Delay.delay(4000);
	}
	//@Test
	public void testDistinct()
	{
		Flux<Integer> items = Flux.just(1,2,3,1,2,3);
		items.distinct()
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
		/*
		Flux.range(0, 1000)
			.take(10)
			 .subscribe(item->{
				System.out.println("Take "+item); 
			 });
		*/
		
		var range = Flux.range(1,100)
			.delayElements(Duration.ofMillis(100));
		range.take(Duration.ofMillis(400))
			 .subscribe(item->{
					System.out.println("Take "+item); 
				 });
		Delay.delay(5000);
	}
	//@Test
	public void takeLast()
	{
		Flux<Integer> items = Flux.just(1,2,3,100,200,300);
		items
			.skip(2)
			//.takeLast(3)
		 	//.takeUntil(item->item>100)
			//.takeWhile(item->item<100)
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
		
		Delay.delay(3000);
	}
	//@Test
	public void testSample()
	{
		Flux<Integer> items = Flux.just(1,2,3,100,200,300)
					.delayElements(Duration.ofMillis(100));
		items
			.sample(Duration.ofMillis(400))
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
		
		Delay.delay(3000);
	}
	@Test
	public void testSingle()
	{
		Flux<Integer> items = Flux.just(300)
					.delayElements(Duration.ofMillis(100));
		items
			.single()
			 .subscribe(item->{
				System.out.println("Item "+item); 
			 });
		
		Delay.delay(3000);
	}
}
