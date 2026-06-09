package com.turing.javaee8.webfluxmongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.webfluxmongo.common.Mapper;
import com.turing.javaee8.webfluxmongo.dto.ReviewDto;
import com.turing.javaee8.webfluxmongo.model.Review;
import com.turing.javaee8.webfluxmongo.repository.MovieRepository;
import com.turing.javaee8.webfluxmongo.repository.ReviewRepository;
import com.turing.javaee8.webfluxmongo.service.ReviewService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	MovieRepository movieDao;
	
	@Autowired
	ReviewRepository reviewDao;
	
	@Autowired
	Mapper mapper;
	
	@Override
	public Mono<ReviewDto> saveReview(ReviewDto dto) {
		String movieId = dto.getMovieId();
		Review review = this.mapper.map(dto, Review.class);
		return this.movieDao
					.findById(movieId)
					.flatMap(movie->{
						review.setMovie(movie);
						return this.reviewDao.save(review);
					})
					.switchIfEmpty(Mono.error(new RuntimeException("Movie "+movieId+" not found")))
					.map(r->{
						ReviewDto reviewDto = this.mapper.map(r, ReviewDto.class);
						reviewDto.setMovieId(movieId);
						return reviewDto;
					});
	}

	@Override
	public Flux<ReviewDto> getReviewByMovieId(String movieId) {
		
		return this.movieDao
				.findById(movieId)//Mono<Movie>
				.flatMapMany(movie->{
					return this.reviewDao.getAllReviewByMovieId(movieId);//Flux<Review>
				})
				.switchIfEmpty(Mono.error(new RuntimeException("Movie "+movieId+" not found")))
				.map(r->{
					ReviewDto reviewDto = this.mapper.map(r, ReviewDto.class);
					reviewDto.setMovieId(movieId);
					return reviewDto;
				});
		
	}

	@Override
	public Mono<ReviewDto> updateReview(ReviewDto dto) {
		String movieId = dto.getMovieId();
		String reviewId = dto.getId();
		Review review = this.mapper.map(dto, Review.class);
		return this.reviewDao
					.findById(reviewId)
					.switchIfEmpty(Mono.error(new RuntimeException("Review "+dto.getId()+" not found")))
					.flatMap(r->{
						return this.movieDao
								.findById(movieId)
								.switchIfEmpty(Mono.error(new RuntimeException("Movie "+movieId+" not found")));
					})
					.flatMap(movie->{
						review.setMovie(movie);
						return this.reviewDao.save(review);
					})
					.map(r->{
						ReviewDto reviewDto = this.mapper.map(r, ReviewDto.class);
						reviewDto.setMovieId(movieId);
						return reviewDto;
					});
	}

	@Override
	public Mono<ReviewDto> deleteReviewById(String reviewId) {
		
		return this.reviewDao
				.findById(reviewId)
				.switchIfEmpty(Mono.error(new RuntimeException("Review "+reviewId+" not found")))
				.flatMap(r->{
					return this.reviewDao
								.deleteById(reviewId)
								.thenReturn(r);
				})
				.map(r->{
					ReviewDto reviewDto = this.mapper.map(r, ReviewDto.class);
					reviewDto.setMovieId(r.getMovie().getId());
					return reviewDto;
				});
	}

}
