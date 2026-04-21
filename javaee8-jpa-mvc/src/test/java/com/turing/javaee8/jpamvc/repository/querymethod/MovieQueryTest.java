package com.turing.javaee8.jpamvc.repository.querymethod;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.repository.MovieDao;
import com.turing.javaee8.jpamvc.repository.MovieDaoTest;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Rollback(false)
@Slf4j
@SpringBootTest
public class MovieQueryTest {

	@Autowired
	MovieDao movieDao;

	@Test
	@Transactional
	public void testQueryByTitle()
	{
		//List<Movie> movies = this.movieDao.findByTitle("Titanic");
		List<Movie> movies = this.movieDao.findByGenreAndYear("Drama",2005);
		movies.forEach(System.out::println);
	}
}
