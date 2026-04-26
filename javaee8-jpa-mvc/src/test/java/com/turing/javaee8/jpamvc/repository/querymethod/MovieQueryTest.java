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
import com.turing.javaee8.jpamvc.model.dto.TitleAndYear;
import com.turing.javaee8.jpamvc.model.dto.TitleWithYear;
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
	
	//@Test
	@Transactional
	public void testJPQL()
	{
		//int effectedRow = this.movieDao.updateMovieGenreById(17L, "Crime");
		//int effectedRow = this.movieDao.deleteMovieByid(17L);
		//int effectedRow = this.movieDao.insertMovie("Test",200,"Drama");
		//System.out.println("Row effected "+effectedRow);
		
		//Movie movie =this.movieDao.getMovieById(2L);
		//List<Movie> movies  = this.movieDao.getAllMovies();
		List<Movie> movies = this.movieDao.getAllMoviesByTitle("men");
		movies.forEach(System.err::println);
	}
	//@Test
	@Transactional
	public void testProjection()
	{
	
		/*
		List<TitleAndYear> titles  = this.movieDao.getAllMovieTitleAndYear();
		for(TitleAndYear title: titles)
		{
			System.err.println("Title "+title.getTitle() +" Year "+title.getYear());
		}
		*/
		/*
		List<String> titles = this.movieDao.getAllMovieTitle();
		titles.forEach(System.err::println);
		*/
		/*
		List<TitleWithYear> titles  = this.movieDao.getAllMovieTitleAndYearWithRecord();
		for(TitleWithYear title: titles)
		{
			System.err.println("Title "+title.title() +" Year "+title.year());
		}
		*/
		List<String> details = this.movieDao.getAllMovieDetails();
		details.forEach(System.err::println);
	}
	
	
	//@Test
	@Transactional
	public void testProjections()
	{
		//Long count = this.movieDao.getTotalMovie();
		//System.err.println("Count "+count);
		//List<String> genres = this.movieDao.getAllMovieWithGenreGte(2);
		List<String> genres = this.movieDao.getAllMovieGenere();
		genres.forEach(System.err::println);
	}
	@Test
	@Transactional
	public void testJoin()
	{
		//List<Movie> movies = this.movieDao.getAllMovieWithActor("Leo");
		//List<Movie> movies = this.movieDao.getAllMovieLimit(5);
		List<Movie> movies = this.movieDao.getAllMovieWithNative();
		movies.forEach(System.err::println);
	}
}
