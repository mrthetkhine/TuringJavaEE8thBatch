package com.turing.javaee8.webfluxmongo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.webfluxmongo.common.ResponseUtil;
import com.turing.javaee8.webfluxmongo.dto.ActorDto;
import com.turing.javaee8.webfluxmongo.dto.MovieDto;
import com.turing.javaee8.webfluxmongo.dto.RestResponse;
import com.turing.javaee8.webfluxmongo.service.ActorService;
import com.turing.javaee8.webfluxmongo.service.MovieService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	ResponseUtil util;
	
	
	@GetMapping
	Mono<ResponseEntity<RestResponse>> getAllMovies()
	{
		return this.actorService
					.getAllActors()
					.collectList()
					.flatMap(actors->this.util.succesResponse(HttpStatus.OK, 
							"Actor List success", actors)
							);
	}
	@GetMapping(value="/{actorId}")
	Mono<ResponseEntity<RestResponse>> getActorById(@PathVariable String actorId)
	{
		return this.actorService
					.getActorById(actorId)
					.flatMap(actor->this.util.succesResponse(HttpStatus.OK, 
							"Actor success", actor)
							)
					.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
	}
	@PostMapping
	Mono<ResponseEntity<RestResponse>> saveActor(@Valid @RequestBody Mono<ActorDto> dtoMono)
	{
		return dtoMono 
				.flatMap(dto->this.actorService.saveActor(dto))
				.flatMap(movie->this.util.succesResponse(HttpStatus.CREATED, 
						"Actor created", movie)
						);
					
	}
	@DeleteMapping(value="/{actorId}")
	Mono<ResponseEntity<RestResponse>> deleteActorById(@PathVariable String actorId)
	{
		return this.actorService
					.deleteAcotrById(actorId)
					.flatMap(actor->this.util.succesResponse(HttpStatus.OK, 
							"Actor success", actor)
							)
					.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
	}
}
