package com.turing.javaee8.jpamvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.bean.Mapper;
import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;
import com.turing.javaee8.jpamvc.repository.MovieDao;
import com.turing.javaee8.jpamvc.service.MovieService;

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

	
}
