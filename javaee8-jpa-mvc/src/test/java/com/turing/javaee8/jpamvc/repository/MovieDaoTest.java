package com.turing.javaee8.jpamvc.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.Comment;
import com.turing.javaee8.jpamvc.model.Director;
import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.MovieDetails;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Rollback(false)
@Slf4j
@SpringBootTest
public class MovieDaoTest {
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	DirectorDao directorDao;
	
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
	@Transactional
	//@Test
	public void testFindAll()
	{
		List<Movie> movies = this.movieDao.findAll();
		for(Movie movie : movies)
		{
			log.info("Movie "+movie);
		}
	}
	@Transactional
	//@Test
	public void testFindById()
	{
		Optional<Movie> result = this.movieDao.findById(2L);
		Movie movie = result.get();
		log.info("Movie "+movie.getComments().size());
		log.info("Movie "+movie.getComments().size());
	}
	@Transactional
	//@Test
	public void testOneToManyComment()
	{
		Optional<Movie> result = this.movieDao.findById(3L);
		Movie movie = result.get();
		
		Comment comment = movie.getComments().get(0);
		comment.setComment("Comment updated");
		
		this.movieDao.save(movie);
	}
	
	@Transactional
	//@Test
	public void testManyToMany()
	{
		Optional<Movie> result = this.movieDao.findById(2L);
		Movie movie = result.get();
		
		Optional<Actor> actorResult =this.actorDao.findById(2L);
		Actor actor = actorResult.get();
		
		movie.getActors().remove(actor);
		this.movieDao.save(movie);
	}
	@Transactional
	//@Test
	public void testManyToManyDirector()
	{
		Optional<Movie> result = this.movieDao.findById(2L);
		Movie movie = result.get();
		
		Optional<Director> directorResult =this.directorDao.findById(1L);
		Director director = directorResult.get();
		
		movie.getDirectors().add(director);
		this.movieDao.save(movie);
	}
	@Transactional
	@Test
	public void testActorIsInMovie()
	{
		Long count = this.movieDao.getMovieCountWithActor(2L, 3L);
		System.out.println("Movie count "+count);
	}
}
