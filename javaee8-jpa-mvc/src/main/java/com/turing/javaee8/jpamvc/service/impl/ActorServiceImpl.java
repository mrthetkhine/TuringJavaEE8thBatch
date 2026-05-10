package com.turing.javaee8.jpamvc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.bean.Mapper;
import com.turing.javaee8.jpamvc.controller.api.exception.ResourceNotFoundException;
import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.dto.ActorDto;
import com.turing.javaee8.jpamvc.repository.ActorDao;
import com.turing.javaee8.jpamvc.service.ActorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ActorServiceImpl implements ActorService{
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	Mapper mapper;
	
	@Override
	public List<ActorDto> getAllActor() {
		List<Actor> actors =  this.actorDao.findAll()	;		
		return this.mapper.mapList(actors,ActorDto.class);
	}

	@Override
	public Optional<ActorDto> getActorById(Long id) {
		return this.actorDao
				.findById(id)
				.map(actor->this.mapper.map(actor,ActorDto.class));
				
	}

	@Override
	public ActorDto saveActor(ActorDto dto) {
		Actor actor = this.mapper.map(dto, Actor.class);
		actor = this.actorDao.save(actor);
		return this.mapper.map(actor, ActorDto.class);
	}

	@Override
	public ActorDto updateActor(ActorDto dto) {
		
		return this.actorDao
			.findById(dto.getId())
			.map(actor->{
				actor.setFirstName(dto.getFirstName());
				actor.setLastName(dto.getLastName());
				actor.setGender(dto.getGender());
				actor.setBirthday(dto.getBirthday());
				
				actor = this.actorDao.save(actor);
				return actor;
			})
			.map(actor->this.mapper.map(actor, ActorDto.class))
			.orElseThrow(()->new ResourceNotFoundException("Actor " +dto.getId()+" Not found"));
		
	}

}
