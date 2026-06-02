package com.turing.javaee8.webfluxmongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.webfluxmongo.model.Movie;
import com.turing.javaee8.webfluxmongo.repository.ActorRepository;
import com.turing.javaee8.webfluxmongo.repository.MovieRepository;
import com.turing.javaee8.webfluxmongo.service.MovieService;

import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieRepository movieDao;
	
	@Autowired
	ActorRepository actorDao;

	@Override
	public Mono<Movie> assignActorToMovie(String movieId, String actorId) {
		return this.movieDao.findById(movieId)
				 .switchIfEmpty(Mono.error(new RuntimeException("Movie id "+movieId+" not found")))	
				 .flatMap(movie->{
					 //System.out.println("movie FlatMap "+movie);
					 return this.actorDao
							 	  .findById(actorId)
							 	  .switchIfEmpty(Mono.error(new RuntimeException("Actor id "+actorId+" not found")))
					 			  .flatMap(actor->{
					 				  	 System.out.println("Actor assigned");
						 				 movie.getActors().add(actor);
						 				 return this.movieDao.save(movie);
					 			  });
				 });
	}

	@Override
	public Mono<Movie> addGenreToMovie(String movieId, String genre) {
		
		return this.movieDao.findById(movieId)
				.switchIfEmpty(Mono.error(new RuntimeException("Movie id "+movieId+" not found")))
				.flatMap(movie->{
					if(movie.getGeneres().contains(genre))
					{
						return Mono.error(new RuntimeException("Genre "+genre+ " Already exist"));
					}
					else
					{
						movie.getGeneres().add(genre);
						return this.movieDao.save(movie);
					}
					
				});
	}
}
