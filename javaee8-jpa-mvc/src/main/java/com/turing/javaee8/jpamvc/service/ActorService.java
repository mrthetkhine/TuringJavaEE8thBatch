package com.turing.javaee8.jpamvc.service;

import java.util.*;

import com.turing.javaee8.jpamvc.model.dto.ActorDto;
public interface ActorService {
	List<ActorDto> getAllActor();
	Optional<ActorDto> getActorById(Long id);
	ActorDto saveActor(ActorDto dto);
	ActorDto updateActor(ActorDto dto);
}
