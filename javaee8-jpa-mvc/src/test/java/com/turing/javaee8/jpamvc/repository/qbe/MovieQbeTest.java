package com.turing.javaee8.jpamvc.repository.qbe;





import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.ArgumentMatchers.startsWith;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.repository.MovieDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Rollback(false)
@Slf4j
@SpringBootTest
public class MovieQbeTest {
	@Autowired
	MovieDao movieDao;
	
	//@Test
	@Transactional
	public void testQbe()
	{
		Movie m = new Movie();
		m.setTitle("Titanic");
		m.setYear(2026);
		List<Movie> movies = this.movieDao.findAll(Example.of(m));
		movies.forEach(System.err::println);
	}
	//@Test
	@Transactional
	public void testWithMatcher()
	{
		Movie moviem = new Movie();
		moviem.setTitle("Tit");
		moviem.setYear(2026);
		ExampleMatcher matcher = ExampleMatcher
					.matching()     
					.withMatcher("title", match -> match.contains())
					//.withStringMatcher(StringMatcher.CONTAINING)
					.withIgnorePaths("year");                         
				  //.withIncludeNullValues()  ;                           
				  //.withStringMatcher(StringMatcher.STARTING); 
		List<Movie> movies = this.movieDao.findAll(Example.of(moviem,matcher));
		movies.forEach(System.err::println);
	}
	@Test
	@Transactional
	public void testSortedBy()
	{
		Movie moviem = new Movie();
		//moviem.setTitle("Tit");
		//moviem.setYear(2026);
		ExampleMatcher matcher = ExampleMatcher
					.matching()     
					.withMatcher("title", match -> match.contains())
					//.withStringMatcher(StringMatcher.CONTAINING)
					.withIgnorePaths("year");
					
				  //.withIncludeNullValues()  ;                           
				  //.withStringMatcher(StringMatcher.STARTING); 
		Sort sort = Sort.by("year").ascending();
		List<Movie> movies = this.movieDao.findAll(Example.of(moviem,matcher),sort);
		movies.forEach(System.err::println);
	}
}
