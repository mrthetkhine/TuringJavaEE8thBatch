package com.turing.javaee8.webfluxmongo.service;

import com.turing.javaee8.webfluxmongo.dto.ActorDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActorService {
	Flux<ActorDto> getAllActors();
	Mono<ActorDto> getActorById(String id);
	Mono<ActorDto> saveActor(ActorDto actorDto);
	Mono<ActorDto> updateActor(ActorDto actorDto);
	Mono<ActorDto> deleteAcotrById(String id);
}
