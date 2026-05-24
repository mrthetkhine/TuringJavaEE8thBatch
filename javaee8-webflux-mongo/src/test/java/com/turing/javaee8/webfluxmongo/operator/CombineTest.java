package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CombineTest {
	//@Test
	public void testCombine()
	{
		var first = Flux.just(1,2,3,4)
						.delayElements(Duration.ofMillis(200));
		var second = Flux.just(4,5,6)
						.delayElements(Duration.ofMillis(100));
		var third = Flux.just("Apple","Orange","Banana")
				.delayElements(Duration.ofMillis(100));
		Flux.concat(first,second)
			.subscribe(System.out::println);
		Flux.merge(first,second)
		.subscribe(item->{
			System.out.println("Item "+item);
		});
		
		Flux.zip(first,third)
			.subscribe(item->{
				System.out.println("Concat "+item.getT1() +" T2 "+item.getT2());
			});
		Delay.delay(3000);
	}
	@Test
	public void testAnd()
	{
		var first = Mono.fromCallable(()->{
			System.out.println("First start");
			Delay.delay(1000);
			System.out.println("First done");
			return 100;
		});
		var second = Mono.fromCallable(()->{
			System.out.println("Second start");
			Delay.delay(3000);
			
			return 200;
		});
		/*
		first.and(second)
		.doFinally(data->{
			System.out.println("Done "+data);
		})
		.subscribe();
		*/
		
		Mono.when(first,second)
		.doFinally(data->{
			System.out.println("When Done "+data);
		})
		
		.subscribe(item->{
			System.out.println("when item "+item);
		});
		
			
			//.
		Delay.delay(3000);
	}
}
