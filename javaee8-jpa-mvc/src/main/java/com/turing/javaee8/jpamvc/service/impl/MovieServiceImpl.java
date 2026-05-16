package com.turing.javaee8.jpamvc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.bean.Mapper;
import com.turing.javaee8.jpamvc.controller.api.exception.InvalidBusinessLogicException;
import com.turing.javaee8.jpamvc.controller.api.exception.ResourceNotFoundException;
import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.MovieDetails;
import com.turing.javaee8.jpamvc.model.dto.ActorDto;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;
import com.turing.javaee8.jpamvc.repository.ActorDao;
import com.turing.javaee8.jpamvc.repository.MovieDao;
import com.turing.javaee8.jpamvc.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	Mapper mapper;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	ActorDao actorDao;
	
	@Override
	public List<MovieDto> getAllMovie() {
		List<Movie> movies = this.movieDao.findAll();
		return this.mapper.mapList(movies, MovieDto.class);
	}

	@Override
	public Optional<MovieDto> getMovieById(Long id) {
		Optional<Movie> movie = this.movieDao.findById(id);
	
		return movie.map(m->this.mapper.map(m, MovieDto.class));
	}

	@Override
	public MovieDto saveMovie(MovieDto dto) {
		
		log.info("Movie DTO "+dto);
		
		
		Movie movie = this.mapper.map(dto, Movie.class);
		movie.getDetails().setMovie(movie);
		log.info("Maped DTO to movie "+movie);
		
		Movie savedMovie = this.movieDao.save(movie);
		
		return this.mapper.map(savedMovie, MovieDto.class);
	}

	@Override
	public MovieDto updateMovie(MovieDto dto) {
		return this.movieDao
			   .findById(dto.getId())
			   .map(movie->{
				   movie.setTitle(dto.getTitle());
				   movie.setYear(dto.getYear());
				   movie.setGenre(dto.getGenre());
				   movie.getDetails().setDetails(dto.getDetails().getDetails());
				   return movie;
			   })
			   .map(movie->{
				   return this.movieDao.save(movie);
			   })
			   .map(movie->this.mapper.map(movie, MovieDto.class))
			   .orElseThrow(()->new ResourceNotFoundException("Movie "+dto.getId() +" not found"));
		
	}

	@Override
	public MovieDto deleteMovieById(Long id) {
		
		return this.movieDao
					.findById(id)
					.map(movie->{
						this.movieDao.deleteById(id);
						return movie;
					})
					.map(movie->this.mapper.map(movie, MovieDto.class))
					.orElseThrow(()->new ResourceNotFoundException("Movie "+id +" not found"));
	}

	@Override
	public List<ActorDto> getAllActorInMovie(Long movieId) {
		return this.movieDao
				.findById(movieId)
				.map(movie->this.mapper.mapSet(movie.getActors(), ActorDto.class))
				.orElseThrow(()->new ResourceNotFoundException("Movie "+movieId +" not found"));
	}

	@Override
	public MovieDto assignActorToMovie(Long movieId, Long actorId) {
		return this.movieDao	
					.findById(movieId)
					.map(movie-> {
						return this.actorDao
								.findById(actorId)
								.map(actor->{
									Long count = this.movieDao.getMovieCountWithActor(movieId, actorId);
									if(count >0)
									{
										throw new InvalidBusinessLogicException("Actor already exist in movi e");
									}
									
									movie.getActors().add(actor);
									return this.movieDao.save(movie);
								})
								.map(m->this.mapper.map(m, MovieDto.class))
								.orElseThrow(()->new ResourceNotFoundException("Actor "+actorId +" not found"));
					})
					.orElseThrow(()->new ResourceNotFoundException("Movie "+movieId +" not found"));
	}

	
}
