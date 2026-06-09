package com.turing.javaee8.webfluxmongo.service;

import com.turing.javaee8.webfluxmongo.dto.ReviewDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {
	Flux<ReviewDto> getReviewByMovieId(String movieId);
	Mono<ReviewDto> saveReview(ReviewDto dto);
	Mono<ReviewDto> updateReview(ReviewDto dto);
	Mono<ReviewDto> deleteReviewById(String reviewId);
	
}
