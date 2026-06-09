package com.turing.javaee8.webfluxmongo.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.model.Review;

import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review,String>{
	@Query("{movie:?0}")
	Flux<Review> getAllReviewByMovieId(String movieId);
}
