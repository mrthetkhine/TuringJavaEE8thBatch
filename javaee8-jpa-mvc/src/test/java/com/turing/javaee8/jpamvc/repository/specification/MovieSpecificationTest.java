package com.turing.javaee8.jpamvc.repository.specification;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.repository.MovieDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Rollback(false)
@Slf4j
@SpringBootTest
public class MovieSpecificationTest {
	@Autowired
	MovieDao movieDao;
	
	@Test
	@Transactional
	public void testSpeicifcation()
	{
		//List<Movie> movies =this.movieDao.findAll(MovieSpecification.getAllMovieInYear(2020L));
		//List<Movie> movies =this.movieDao.findAll(MovieSpecification.getMovieByTitleYear(null,1997L));
		//List<Movie> movies =this.movieDao.findAll(MovieSpecification.getAllMovieWithActorIn("Kate"));
		//List<Movie> movies =this.movieDao.findAll(MovieSpecification.getAllMovieWithActorOrDirector("Kate","Christopher"));
		//List<Movie> movies =this.movieDao.findAll(MovieSpecification.getAllMovieWithDirector("Cameron"));
		List<Movie> movies =this.movieDao.findAll(MovieSpecification.getAllMovieOrderByYear());
		movies.forEach(System.err::println);
	}
	//@Test
	@Transactional
	public void testJoin()
	{
		Movie movie =this.movieDao.getMovieById(3L);
		System.err.println("Movie "+movie);
	}
	//@Test
	@Transactional
	public void testGroupBy()
	{
		
		//List<String> genres =this.movieDao.findAll(MovieSpecification.getAllGenereWithMovieGt(2L));
		
		//genres.forEach(System.err::println);
	}
}
