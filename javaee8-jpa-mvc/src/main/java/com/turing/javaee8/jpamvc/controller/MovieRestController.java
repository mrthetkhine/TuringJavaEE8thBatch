package com.turing.javaee8.jpamvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.jpamvc.model.dto.MovieDto;
import com.turing.javaee8.jpamvc.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="api/movies")
public class MovieRestController {

	@Autowired
	MovieService movieService;
	
	@GetMapping
	List<MovieDto> getAllMovies()
	{
		List<MovieDto> movies = this.movieService.getAllMovie();
		log.info("Movie length "+movies.size());
		return movies;
	}
}
