package com.turing.javaee8.jpamvc.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.MovieDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MovieDaoTest {
	@Autowired
	MovieDao movieDao;
	
	//@Test
	public void testSaveMovie()
	{
		Movie movie =new Movie();
		movie.setTitle("Third");
		movie.setYear(2005);
		movie.setGenre("Drama");
		
		MovieDetails details =new MovieDetails();
		details.setDetails("A Good flim");
		
		movie.setDetails(details);
		details.setMovie(movie);
		
		this.movieDao.save(movie);
	}
	@Test
	public void testFindById()
	{
		Optional<Movie> movie = this.movieDao.findById(2L);
		log.info("Movie "+movie.get());
	}
}
