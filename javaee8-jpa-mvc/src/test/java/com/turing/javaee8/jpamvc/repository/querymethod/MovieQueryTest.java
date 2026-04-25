package com.turing.javaee8.jpamvc.repository.querymethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	//@Test
	@Transactional
	public void testQueryByTitle()
	{
		//List<Movie> movies = this.movieDao.findByTitleIgnoreCase("Tit");
		//List<Movie> movies = this.movieDao.findByGenreAndYear("Drama",2005);
		//List<Movie> movies = this.movieDao.findByGenreOrderByYearDesc("Drama");
		//List<Movie> movies = this.movieDao.findByYearAfter(2000);
		//List<Movie> movies = this.movieDao.findByTitleLike("%men");
		//List<Movie> movies = this.movieDao.findByGenreNot("Drama");
		List<String> genres =new ArrayList<>();
		genres.add("Crime");
		genres.add("Action");
		List<Movie> movies = this.movieDao.findByGenreIn(genres);
		movies.forEach(System.err::println);
	}
	
	//@Test
	@Transactional
	public void testPagination()
	{
		PageRequest page = PageRequest.of(1, 5);
		Page<Movie> movies  = this.movieDao.findAll(page);
		movies.forEach(System.err::println);
	}
	//@Test
	@Transactional
	public void testSort()
	{
		
		List<Movie> movies  = this.movieDao.findAll(Sort.by("year").descending());
		movies.forEach(System.err::println);
	}
	
	@Test
	@Transactional
	public void testJPQL()
	{
		//int effectedRow = this.movieDao.updateMovieGenreById(17L, "Crime");
		//int effectedRow = this.movieDao.deleteMovieByid(17L);
		//int effectedRow = this.movieDao.insertMovie("Test",200,"Drama");
		//System.out.println("Row effected "+effectedRow);
		
		//Movie movie =this.movieDao.getMovieById(2L);
		List<Movie> movies  = this.movieDao.getAllMovies();
		movies.forEach(System.err::println);
	}
}
