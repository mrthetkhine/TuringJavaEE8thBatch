package com.turing.javaee8.webfluxmongo.operator;

import org.junit.jupiter.api.Test;

import io.netty.util.Signal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PeekTest {
	@Test
	public void testPeek()
	{
		Flux<Integer> items = Flux.just(1,2,3,4);
		
		items 
			
			.map(item->{
				if(item>2)
				{
					throw new RuntimeException("Item >2");
				}
				else
				{
					return item;
				}
			})
			.switchIfEmpty(Mono.just(100))
			.doOnNext(item->{
				System.out.println("Item "+item);
			})
			.doOnEach(singal->{
				System.out.println("doOnEach "+singal.toString());
			})
			.doOnComplete(()->{
				System.out.println("OnComplete ");
			})
			.doOnError(err->{
				System.out.println("doOnError "+err.getMessage());
			})
			.doOnTerminate(()->{
				System.out.println("doOnTerminate ");
			})
			.doFinally(signal->{
				System.out.println("doFinally "+signal.toString());
			})
			
			.doOnSubscribe(item->{
				System.out.println("onSubscribe");
			})
			.log()
			.subscribe(success->{
				System.out.println("Success "+success);
			},err->{
				System.out.println("Error-> "+err.getMessage());
			});
		Delay.delay(1000);
	}
}
