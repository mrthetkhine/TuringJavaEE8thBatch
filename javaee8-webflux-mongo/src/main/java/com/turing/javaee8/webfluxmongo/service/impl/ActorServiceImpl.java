package com.turing.javaee8.webfluxmongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.webfluxmongo.common.Mapper;
import com.turing.javaee8.webfluxmongo.dto.ActorDto;
import com.turing.javaee8.webfluxmongo.dto.MovieDto;
import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.repository.ActorRepository;
import com.turing.javaee8.webfluxmongo.service.ActorService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ActorServiceImpl implements ActorService{
	
	@Autowired
	ActorRepository actorDao;
	
	@Autowired
	Mapper mapper;
	
	@Override
	public Flux<ActorDto> getAllActors() {
		
		return this.actorDao
					.findAll()
					.map(actor->this.mapper.map(actor, ActorDto.class));
	}

	@Override
	public Mono<ActorDto> getActorById(String id) {
		
		return this.actorDao
					.findById(id)
					.switchIfEmpty(Mono.error(new RuntimeException("Actor id "+id+" not found")))
					.map(actor->this.mapper.map(actor, ActorDto.class));
	}

	@Override
	public Mono<ActorDto> saveActor(ActorDto actorDto) {
		
		Actor actor = this.mapper.map(actorDto, Actor.class);
		return this.actorDao
					.save(actor)
					.map(act->this.mapper.map(act, ActorDto.class));
	}

	@Override
	public Mono<ActorDto> deleteAcotrById(String id) {
		return this.actorDao
				.findById(id)
				.switchIfEmpty(Mono.error(new RuntimeException("Actor id "+id+" not found")))
				.flatMap(actor->{
					return this.actorDao
							.deleteById(id)
							.then(Mono.just(actor));
					
				})
				.map(act->this.mapper.map(act,ActorDto.class));
	}

}
