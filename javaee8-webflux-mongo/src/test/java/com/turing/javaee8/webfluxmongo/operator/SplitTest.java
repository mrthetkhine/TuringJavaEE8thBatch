package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class SplitTest {
	@Test
	public void testSplit()
	{
		var items = Flux.just(1,2,3,4,5)
						.delayElements(Duration.ofMillis(100));
		
		items.window(Duration.ofMillis(300))
			.subscribe(data->{
				System.out.println("Dat "+data.toIterable());
				data
				.subscribe(item->{
					System.out.println("Item "+item);
				});
					
			});
		Delay.delay(3000);
	}
}
