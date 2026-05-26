package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CombineTest {
	static Mono<Integer> getData(int i)
	{
		if(i>0)
		{
			return Mono.just(i);
		}
		else
		{
			return Mono.empty();
		}
	}
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
	//@Test
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
	//@Test
	public void testRepeat()
	{
		var first = Flux.just(1,2,3,4)
						.delayElements(Duration.ofMillis(200))
						.repeat(3);
		first.subscribe(item->{
			System.out.println("Repat item "+item);
		});
		Delay.delay(5000);
	}
	//@Test
	public void testEmptySwitch()
	{
		var first = getData(10);
		
		first
		//.defaultIfEmpty(100)
		.switchIfEmpty(Mono.just(100))
		.map(item->{
			System.out.println("Map "+item);
			return item*2;
		})
		.subscribe(item->{
			System.out.println("GetData item "+item);
		});
		Delay.delay(2000);
	}
	//@Test
	public void testIgnore()
	{
		var first = getData(10);
		
		first
		.ignoreElement()
		.map(item->{
			System.out.println("Map "+item);
			return item*2;
		})
		.subscribe(item->{
			System.out.println("GetData item "+item);
		});
		
		Delay.delay(2000);
	}
	//@Test
	public void testThen()
	{
		Mono<Integer> first = Mono.defer(()->{
			System.err.println("Execute first");
			return Mono.just(100)
						.delayElement(Duration.ofMillis(2000))
						.map(item->{
							System.out.println("Emit first");
							return item;
						});
		});
		Mono<Integer> second = Mono.defer(()->{
			System.err.println("Execute second");
			return Mono.just(200)
						.delayElement(Duration.ofMillis(1000))
						.map(item->{
							System.out.println("Emit second");
							return item;
						});
		});
		Mono<Void> third = Mono.defer(()->{
			System.err.println("Execute third");
			return Mono.just(100)
						.delayElement(Duration.ofMillis(1000))
						.map(item->{
							System.out.println("Emit third");
							return item;
						}).then();
						
		});
		/*
		first.then(second)
			 .subscribe();
			 */
		/*
		first.thenEmpty(third)
		 	 .subscribe();
		 	 */
		/*
		first
		//.thenReturn(100)
			.subscribe(item->{
				System.out.println("thenRetrun "+item);
			});
			*/
		first
			.thenMany(Flux.just(1,2,3))
			.subscribe(item->{
				System.out.println("thenMany "+item);
			});
		
		Delay.delay(4000);
	}
	@Test
	public void testDelayUntil()
	{
		Flux<Integer> items = Flux.just(1,2,3);
		
		items
		.delayUntil(item->Mono.just(item)
							  .delayElement(Duration.ofMillis(100))	)
		.map(item->{
			System.out.println("Map "+item);
			return item*2;
		})
		.subscribe(item->{
			System.out.println("GetData item "+item);
		});
		
		Delay.delay(2000);
	}
}
