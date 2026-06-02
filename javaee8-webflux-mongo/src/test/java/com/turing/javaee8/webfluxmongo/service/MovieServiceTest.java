package com.turing.javaee8.webfluxmongo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.webfluxmongo.operator.Delay;
import com.turing.javaee8.webfluxmongo.repository.TestMovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Rollback(false)
@SpringBootTest
//@DataMongoTest
@AutoConfigureDataMongo
public class MovieServiceTest {
	
	@Autowired
	MovieService movieService;
	
	@Test
	public void testAssignGenreToMovie()
	{
		String movieId = "6a1eddae0b28c1c36e45007d";
		String genre="Drama";
		this.movieService.addGenreToMovie(movieId, genre)
						.subscribe(movie->{
							System.out.println("Assigned ");
						},err->{
							System.out.println("Err "+err);
							
						});
		Delay.delay(3000);
	}
}
