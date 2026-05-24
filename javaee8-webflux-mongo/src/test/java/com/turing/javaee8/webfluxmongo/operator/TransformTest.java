package com.turing.javaee8.webfluxmongo.operator;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransformTest {
	
	static Mono<Integer> div(int a,int b)
	{
		if(b==0)
		{
			return Mono.error(new RuntimeException("Divisor is zero"));
		}
		else
		{
			return Mono.just(a/b);
		}
		
	}
	static Mono<String> toMono(String str)
	{
		
		return Mono.just(str);
	}
	//@Test
	public void testMap()
	{
		Flux<String> items = Flux.just("Apple","Orange","Banana");
		Flux<String> items2 = items.map(str->str.toUpperCase());
		Flux<Integer> items3 = items.map(str->str.length());
		items.subscribe(str->{
			System.out.println("Str "+str);
		});
		items2.subscribe(str->{
			System.out.println("Item2 "+str);
		});
	
		items3.subscribe(len->{
			System.out.println("Length "+len);
		});
	}
	//@Test
	public void testFlatMap()
	{
		Flux<String> items = Flux.just("Apple","Orange","Banana");
		
		items.map(item->toMono(item))//Flux<Mono,Mono,Mono>
		 .subscribe(data->{
			System.out.println("DatawithMap "+data); 
		 });
		items.flatMap(item->toMono(item))//Flux<String,String,String>
			 .subscribe(data->{
				System.out.println("Data "+data); 
			 });
	}
	//@Test
	public void testError()
	{
		div(10,2)
			.map(x->x+1)
			.subscribe(result->{
				System.out.println("Result "+result);
			},err->{
				System.out.println("Error "+err);
			});
	}
	//@Test
	public void testDelayElements()
	{
		Flux<Integer> items = Flux.range(0, 4)
									.delayElements(Duration.ofMillis(1000));
		items.subscribe(item->{
			System.out.println("Item "+item);
		});
		Delay.delay(5000);
	}
	//@Test
	public void testConcatMap()
	{
		// .concatMap(item -> this.delayReplyFor(item.id, item.delay))
		 Flux
                .just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))//
                //.flatMap(item -> this.delayReplyFor(item.id, item.delay))
                .concatMap(item -> this.delayReplyFor(item.id, item.delay))
                .subscribe(item->{
                	System.out.println("Item "+item);
                });
		 Delay.delay(2000);
	}
	private Flux<Integer> delayReplyFor(Integer i, long delay) {
        return Flux.just(i).delayElements(Duration.ofMillis(delay));
	}
	@AllArgsConstructor
    static class Pair {
        private int id;
        private long delay;
    }

	@Test
	public void testSwitchMap()
	{
		Flux<String> source = Flux //
                .just("re", "rea", "reac", "react", "reactive") //
                .delayElements(Duration.ofMillis(100))//
                .switchMap(this::lookup);
		source.subscribe(item->{
        	System.out.println("Item "+item);
        });
		Delay.delay(2000);
	}
	private Flux<String> lookup(String word) {
        return Flux.just(word + " -> reactive")//
                .delayElements(Duration.ofMillis(500));
}
}
