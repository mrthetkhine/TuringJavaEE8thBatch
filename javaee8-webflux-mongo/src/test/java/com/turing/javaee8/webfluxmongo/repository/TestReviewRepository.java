package com.turing.javaee8.webfluxmongo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.webfluxmongo.model.Review;
import com.turing.javaee8.webfluxmongo.operator.Delay;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Slf4j
@Rollback(false)
@SpringBootTest
//@DataMongoTest
@AutoConfigureDataMongo
public class TestReviewRepository {
	@Autowired
	ReviewRepository reviewDao;
	
	@Autowired
	MovieRepository movieDao;
	
	//@Test
	public void testGetAllReviewByMovie()
	{
		Flux<Review> reviews = this.reviewDao.getAllReviewByMovieId("6a241aaef548637926a0a557");
		
		reviews.subscribe(data->{
			System.out.println("Review "+data);
		});
		Delay.delay(3000);
	}
	Tuple2<String,Float> getAverageRating(List<Review> reviews)
	{
		float total = 0;
		for(Review r : reviews)
		{
			total += r.getRating();
		}
		float average = total/reviews.size();
		
		String movieId = reviews.get(0).getMovie().getId();
		return Tuples.of(movieId,average);
	}
	@Test
	public void testGetAverageRating()
	{
		float averageRating = 4;
		this.reviewDao 
				.findAll()
				.groupBy(review->{
					return review.getMovie().getId();
				})
				.flatMap(fluxGroup->fluxGroup.collectList())
				.map(this::getAverageRating)
				.filter(tuple->tuple.getT2()>=averageRating )
				.map(tuple->tuple.getT1())
				.flatMap(movieId->this.movieDao.findById(movieId))
				.subscribe(data->{
					
					System.out.println("Movie "+data );
					
				});
		Delay.delay(3000);
	}
}
