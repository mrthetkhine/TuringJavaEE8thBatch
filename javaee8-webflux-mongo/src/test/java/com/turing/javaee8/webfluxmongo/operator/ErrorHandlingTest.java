package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandlingTest {
	static Mono<Integer> div(int a, int b)
	{
		if(b==0)
		{
			return Mono.error(new RuntimeException("Divisor is zero"));
			//throw new RuntimeException("Divisor is zero");
		}
		else
		{
			return Mono.just(a/b);
		}
	}
	//@Test
	public void testError()
	{
		div(10,0)
			.map(item->item+1)
			.subscribe(item->{
				System.out.println("Succes "+item);
			},err->{
				System.out.println("Error "+err.getMessage());
			});
	}
	//@Test
	public void testThenError()
	{
		Mono.just(100)
			.map(item->item+1)
			.then(Mono.error(new RuntimeException("Something went wrong")))
			.subscribe(item->{
				System.out.println("Succes "+item);
			},err->{
				System.out.println("Error "+err.getMessage());
			});
	}
	//@Test
	public void testTimeout()
	{
		Mono.just(100)
			.delayElement(Duration.ofMillis(1000))
			.timeout(Duration.ofMillis(700))
			.subscribe(item->{
				System.out.println("Succes "+item);
			},err->{
				System.out.println("Error "+err.getMessage());
			});
		Delay.delay(4000);
	}
	//@Test
	public void testOnError()
	{
		div(10,0)
			.onErrorReturn(0)
			.map(item->item+1)
			.subscribe(item->{
				System.out.println("Succes "+item);
			},err->{
				System.out.println("Error "+err.getMessage());
			});
		Delay.delay(4000);
	}
	@Test
	public void testOnErrorComplete()
	{
		Flux<Integer> items = Flux.just(1,2,3,4,5,6);
		
		items 
			
			.map(item->{
				if(item==3)
				{
					throw new RuntimeException("Item ==3");
				}
				else
				{
					return item;
				}
			})
			//.onErrorComplete()
			//.onErrorResume(err->Mono.just(500))
			//.onErrorMap(err->new RuntimeException("Wrapper "+err.getMessage()))
			.retry(3)
			.doOnNext((item)->{
				System.out.println("doOnNext "+item);
			})
			.doOnComplete(()->{
				System.out.println("doOnComplete");
				})
			.subscribe();
	}
}
