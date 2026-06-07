package com.turing.javaee8.webfluxmongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.webfluxmongo.common.Mapper;
import com.turing.javaee8.webfluxmongo.dto.MovieDto;
import com.turing.javaee8.webfluxmongo.model.Movie;
import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.repository.ActorRepository;
import com.turing.javaee8.webfluxmongo.repository.MovieRepository;
import com.turing.javaee8.webfluxmongo.service.MovieService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Predicate;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieRepository movieDao;
	
	@Autowired
	ActorRepository actorDao;

	@Autowired
	Mapper mapper;
	@Override
	public Mono<MovieDto> assignActorToMovie(String movieId, String actorId) {
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
				 })
				 .map(movie->this.mapper.map(movie, MovieDto.class));
	}

	@Override
	public Mono<MovieDto> addGenreToMovie(String movieId, String genre) {
		
		return this.movieDao.findById(movieId)
				.switchIfEmpty(Mono.error(new RuntimeException("Movie id "+movieId+" not found")))
				.flatMap(movie->{
					if(movie.getGenres().contains(genre))
					{
						return Mono.error(new RuntimeException("Genre "+genre+ " Already exist"));
					}
					else
					{
						movie.getGenres().add(genre);
						return this.movieDao.save(movie);
					}
					
				})
				.map(movie->this.mapper.map(movie, MovieDto.class));
	}

	@Override
	public Flux<MovieDto> getAllMovie() {
		
		return this.movieDao.findAll()
					.map(movie->this.mapper.map(movie, MovieDto.class));
	}

	@Override
	public Mono<MovieDto> getMovieById(String id) {
		return this.movieDao
				.findById(id)
				.switchIfEmpty(Mono.error(new RuntimeException("Movie id "+id +" not found")))
				.map(movie->this.mapper.map(movie, MovieDto.class));
	}

	@Override
	public Mono<MovieDto> saveMovie(MovieDto movieDto) {
		
		Movie movie = this.mapper.map(movieDto, Movie.class);
		return this.movieDao
					.save(movie)
					.map(m->this.mapper.map(m, MovieDto.class));
	}

	@Override
	public Mono<MovieDto> updateMovie(MovieDto dto) {
		
		return this.movieDao
				.findById(dto.getId())
				.switchIfEmpty(Mono.error(new RuntimeException("Movie id "+dto.getId() +" not found")))
				.flatMap(movie->{
					movie.setTitle(dto.getTitle());
					movie.setYear(dto.getYear());
					movie.getDetails().setDetails(dto.getDetails().getDetails());
					movie.setGenres(dto.getGenres());
					
					return this.movieDao.save(movie);
				})
				.map(m->this.mapper.map(m, MovieDto.class));
	}

	@Override
	public Mono<MovieDto> deleteMovie(String id) {
		
		return this.movieDao
				.findById(id)
				.switchIfEmpty(Mono.error(new RuntimeException("Movie id "+id +" not found")))
				.flatMap(movie->{
					return this.movieDao
							.deleteById(id)
							.then(Mono.just(movie));
					
				})
				.map(m->this.mapper.map(m, MovieDto.class));
	}

	@Override
	public Flux<MovieDto> getAllMovieWhereActorIn(String firstName) {
		
		return this.actorDao
					.findByFirstName(firstName)
					.flatMapMany(actor->{
						return this.movieDao.getMovieWithActorId(actor.getId());
					})
					.map(m->this.mapper.map(m, MovieDto.class));
	}

	@Override
	public Flux<MovieDto> getAllMovieWhereActorInV2(String firstName) {
		
		return this.movieDao
				.findAll()
				.filter(movieContainsActor(firstName))
				.map(m->this.mapper.map(m, MovieDto.class));
	}

	private Predicate<? super Movie> movieContainsActor(String firstName) {
		return movie->{
			boolean containActor = false;
			
			for(Actor act : movie.getActors())
			{
				if(act.getFirstName().contains(firstName))
				{
					containActor = true;
				}
			}
			return containActor;
		};
	}
}
