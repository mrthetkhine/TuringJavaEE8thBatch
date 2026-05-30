package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class SyncTest {
	//@Test
	public void testSync()
	{
		var items = Flux.just(1,2,3,4)
						.delayElements(Duration.ofMillis(100));
		var items2 = Flux.just(100,200,300,400)
				.delayElements(Duration.ofMillis(100));
		items.subscribe(data->{
			System.out.println("Data "+data);
		});
		Integer data = items.blockLast();
		System.out.println("First "+data);
		System.out.println("Done");
		
		items2.subscribe(item->{
			System.out.println("Item 2 "+item );
		});
		Delay.delay(4000);
	}
	@Test
	public void testIterable()
	{
		var items = Flux.just(1,2,3,4)
						.delayElements(Duration.ofMillis(100));
		
		items.toIterable()
			.forEach(System.out::println);
		System.out.println("Done");
		
	
		Delay.delay(4000);
	}
}
