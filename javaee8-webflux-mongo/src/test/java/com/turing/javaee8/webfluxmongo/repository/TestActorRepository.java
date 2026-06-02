package com.turing.javaee8.webfluxmongo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.operator.Delay;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
@Rollback(false)
@SpringBootTest
//@DataMongoTest
@AutoConfigureDataMongo
//@TestPropertySource(locations = "classpath:application.properties")
public class TestActorRepository {
	
	@Autowired
	ActorRepository actorDao;
	
	//@Test
	void testSave()
	{
		Actor actor = new Actor();
		actor.setFirstName("Nicole");
		actor.setLastName("Kidman");
		actor.setGender("Female");
		
		Mono<Actor> result =this.actorDao.save(actor);
			result.subscribe(data->{
				System.out.println("saved actor "+data);
			});
		StepVerifier.create(result)
					.expectNextCount(1)
					.expectComplete();
		Delay.delay(2000);
	}
	//@Test
	void testFindAll()
	{
		this.actorDao.findAll()
					.filter(actor->actor.getGender().equals("Female"))
					.map(actor->actor.getFirstName() + ' '+actor.getLastName())
					.subscribe(System.out::println);
		
		Delay.delay(2000);
	}
	@Test
	void testFindById()
	{
		Actor dummy = new Actor();
		dummy.setFirstName("Dummy");
		dummy.setLastName("Actor");
		this.actorDao.findById("6a1c3a1024ff462a99abb26b")
					 .switchIfEmpty(Mono.error(new RuntimeException("Actor not found")))
					 .onErrorReturn(dummy)
					 .subscribe(actor->{
						System.out.println("Actor "+actor); 
					 },err->{
						 System.out.println("Err "+err.getMessage()); 
					 });	
		Delay.delay(2000);
	}
}
