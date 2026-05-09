package com.turing.javaee8.jpamvc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.bean.Mapper;
import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.MovieDetails;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;
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
		
		/*
		Movie movie = new Movie();
		
		movie.setTitle(dto.getTitle());
		movie.setYear(dto.getYear());
		movie.setGenre(dto.getGenre());
		
		MovieDetails details = new MovieDetails();
		//details.setDetails(dto.getDetails().getDetails());
		movie.setDetails(details);
		*/
		Movie movie = this.mapper.map(dto, Movie.class);
		Movie savedMovie = this.movieDao.save(movie);
		
		return this.mapper.map(savedMovie, MovieDto.class);
	}

	
}
