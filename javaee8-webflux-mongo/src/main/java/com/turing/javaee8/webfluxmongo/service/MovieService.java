package com.turing.javaee8.webfluxmongo.service;
import com.turing.javaee8.webfluxmongo.dto.MovieDto;
import com.turing.javaee8.webfluxmongo.model.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
	Flux<MovieDto> getAllMovie();
	Mono<MovieDto> getMovieById(String id);
	Mono<MovieDto> saveMovie(MovieDto movie);
	Mono<MovieDto> deleteMovie(String id);
	Mono<MovieDto> updateMovie(MovieDto movie);
	Mono<MovieDto> assignActorToMovie(String movieId,String actorId);
	Mono<MovieDto> addGenreToMovie(String movieId,String genre);
	Flux<MovieDto> getAllMovieWhereActorIn(String firstName);
	Flux<MovieDto> getAllMovieWhereActorInV2(String firstName);
}
