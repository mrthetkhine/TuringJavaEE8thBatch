package com.turing.javaee8.webfluxmongo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.model.Movie;
import com.turing.javaee8.webfluxmongo.model.MovieDetails;
import com.turing.javaee8.webfluxmongo.operator.Delay;
import com.turing.javaee8.webfluxmongo.service.MovieService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
@Rollback(false)
@SpringBootTest
//@DataMongoTest
@AutoConfigureDataMongo
//@TestPropertySource(locations = "classpath:application.properties")
public class TestMovieRepository {
	
	@Autowired
	MovieRepository movieDao;
	
	@Autowired
	ActorRepository actorDao;
	
	@Autowired
	MovieService movieService;
	//@Test
	void testSave()
	{
		Movie movie = new Movie();
		movie.setTile("Forrest Gump");
		
		movie.getGeneres().add("Comedy");
		movie.getGeneres().add("Romance");

		movie.setDirector("Robert Zemeckis");
		
		MovieDetails details = new MovieDetails();
		details.setDetails("Award winning movie");
		
		movie.setDetails(details);
		
		this.movieDao.save(movie)
				.subscribe(savedMovie->{
					System.out.println("Movie saved "+savedMovie);
				});
		Delay.delay(2000);
	}
	//@Test
	void testAssignActorToMovie()
	{
		String movieId = "6a1ed9ec244d2476da545087";//
		String actorId = "6a1c38fa5fc7f56a6e9eb282";
		this.movieService.assignActorToMovie(movieId, actorId)
					 .subscribe(savedMovie->{
						 System.out.println("Actor assigned to movie "+savedMovie);
					 });	
		Delay.delay(2000);
	}
	@Test
	void testQueryMethdo()
	{
		this.movieDao.findByYear(1997)
						.subscribe(movie->{
							 System.out.println("Movie "+movie);
						 });
		Delay.delay(2000);
	}
}
