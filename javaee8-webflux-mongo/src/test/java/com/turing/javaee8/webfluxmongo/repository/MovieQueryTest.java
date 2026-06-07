package com.turing.javaee8.webfluxmongo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.webfluxmongo.operator.Delay;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Rollback(false)
@SpringBootTest
//@DataMongoTest
@AutoConfigureDataMongo
public class MovieQueryTest {

	@Autowired
	MovieRepository movieDao;
	
	@Test
	void testQueryMethod()
	{
		this.movieDao
			//.getMovieWithYear(1994)
			//.findByGenres("Action")
			//.getMovieWithGenre("Crime")
			//.getMovieWithGenreSize(2-1)
			//.getAllMovieWithLookup("Leonardo")
			.getMovieWithActorId("6a1c38d8506eba29e62b1cf8")
						.subscribe(movie->{
							 System.out.println("Movie "+movie);
						 });
		Delay.delay(2000);
	}
}
