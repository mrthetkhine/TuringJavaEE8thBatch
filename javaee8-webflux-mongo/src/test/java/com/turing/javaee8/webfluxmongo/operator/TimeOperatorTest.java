package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class TimeOperatorTest {
	//@Test
	void testTime()
	{
		var items = Flux.just(1,2,3,4)
						.delayElements(Duration.ofMillis(
								(long) (Math.random()*1000)
								));
		items.elapsed()
			 .subscribe(tuple->{
				System.out.println("Time "+tuple.getT1() +" Dat "+tuple.getT2()); 
			 });
		Delay.delay(4000);
	}
	//@Test
	void testTimeout()
	{
		var items = Flux.just(1,2,3,4)
						.delayElements(Duration.ofMillis(2000));
		items.timeout(Duration.ofMillis(2500))
			 .subscribe(data->{
				System.out.println("Data "+data); 
			 });
		Delay.delay(4000);
	}
	@Test
	void testInterval()
	{
		Flux.interval(Duration.ofMillis(1000))
		     .subscribe(data->{
		    	System.out.println("Data "+data); 
		     });
		Delay.delay(4000);
	}
}
