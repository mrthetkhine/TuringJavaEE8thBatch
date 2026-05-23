package com.turing.javaee8.webfluxmongo.operator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

@Slf4j
public class AsyncTest {
	private final ExecutorService executorService = Executors.newFixedThreadPool(3);

	@Test
	public void testAsync() {
		Flux<Integer> integers = Flux.create(emitter -> this.launch(emitter, 5));
		Flux<Integer> integers2 = Flux.create(emitter -> this.launch(emitter, 5));
		integers.subscribe(data->{
			System.out.println("Data1 "+data);
		});
		integers2.subscribe(data->{
			System.out.println("Data2 "+data);
		});
		System.out.println("Start");
		
		Flux.zip(integers, integers2)
			.subscribe(data->{
				System.out.println("Pair "+data.getT1()+ " , "+data.getT2());
			});
		StepVerifier.create(integers.doFinally(signalType -> this.executorService.shutdown()))
					.expectNextCount(5)
					.expectComplete();
		try {
			Thread.sleep(6000);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	private void launch(FluxSink<Integer> integerFluxSink, int count) {
		this.executorService.submit(() -> {
			var integer = new AtomicInteger();

			while (integer.get() < count) {
				//System.out.println("Intger "+integer.get());
				double random = Math.random();
				integerFluxSink.next(integer.incrementAndGet());
				this.sleep((long) (random * 1_000));
			}
			
			integerFluxSink.complete();
		});
	}

	private void sleep(long s) {
		try {
			Thread.sleep(s);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
