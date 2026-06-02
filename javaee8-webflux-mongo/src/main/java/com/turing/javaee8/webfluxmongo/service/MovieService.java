package com.turing.javaee8.webfluxmongo.service;
import com.turing.javaee8.webfluxmongo.model.*;

import reactor.core.publisher.Mono;

public interface MovieService {
	Mono<Movie> assignActorToMovie(String movieId,String actorId);
	Mono<Movie> addGenreToMovie(String movieId,String genre);
}
