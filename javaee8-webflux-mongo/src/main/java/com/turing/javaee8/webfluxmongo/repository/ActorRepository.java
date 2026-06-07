package com.turing.javaee8.webfluxmongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


import com.turing.javaee8.webfluxmongo.model.Actor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ActorRepository extends ReactiveMongoRepository<Actor,String>{
	Mono<Actor> findByFirstName(String name);
}
