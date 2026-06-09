package com.turing.javaee8.webfluxmongo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.webfluxmongo.common.ResponseUtil;
import com.turing.javaee8.webfluxmongo.dto.MovieDto;
import com.turing.javaee8.webfluxmongo.dto.RestResponse;
import com.turing.javaee8.webfluxmongo.dto.ReviewDto;
import com.turing.javaee8.webfluxmongo.service.MovieService;
import com.turing.javaee8.webfluxmongo.service.ReviewService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	ResponseUtil util;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping
	Mono<ResponseEntity<RestResponse>> getAllMovies()
	{
		return this.movieService
					.getAllMovie()
					.collectList()
					.flatMap(movies->this.util.succesResponse(HttpStatus.OK, 
							"Movie List success", movies)
							);
	}
	@GetMapping(value="/{movieId}")
	Mono<ResponseEntity<RestResponse>> getMovieById(@PathVariable String movieId)
	{
		return this.movieService
					.getMovieById(movieId)
					.flatMap(movies->this.util.succesResponse(HttpStatus.OK, 
							"Movie success", movies)
							)
					.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
	}
	@PostMapping
	Mono<ResponseEntity<RestResponse>> saveMovie(@Valid @RequestBody Mono<MovieDto> dtoMono)
	{
		return dtoMono 
				.flatMap(dto->this.movieService.saveMovie(dto))
				.flatMap(movie->this.util.succesResponse(HttpStatus.CREATED, 
						"Movie created", movie)
						);
					
	}
	@PutMapping(value="/{movieId}")
	Mono<ResponseEntity<RestResponse>> updateMovie(@PathVariable String movieId,@Valid @RequestBody Mono<MovieDto> dtoMono)
	{
		return dtoMono 
				.map(dto->{
					dto.setId(movieId);
					return dto;
				})
				.flatMap(dto->this.movieService.updateMovie(dto))
				.flatMap(movie->this.util.succesResponse(HttpStatus.OK, 
						"Movie updated", movie)
						)
				.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
	@DeleteMapping(value="/{movieId}")
	Mono<ResponseEntity<RestResponse>> deleteMovie(@PathVariable String movieId)
	{
		return  this.movieService.deleteMovie(movieId)
				.flatMap(movie->this.util.succesResponse(HttpStatus.OK, 
						"Movie deleted", movie)
						)
				.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
	@PostMapping(value="/{movieId}/assignment/actors/{actorId}")
	Mono<ResponseEntity<RestResponse>> assignActorToMovie(@PathVariable String movieId,@PathVariable String actorId)
	{
		return this.movieService.assignActorToMovie(movieId, actorId)
				.flatMap(movie->this.util.succesResponse(HttpStatus.OK, 
						"Actor assigned to movie", movie)
						)
				.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
	@GetMapping(value="/{movieId}/reviews")
	Mono<ResponseEntity<RestResponse>> getAllReviewByMovieId(@PathVariable String movieId)
	{	
	return  this.reviewService.getReviewByMovieId(movieId)
			.collectList()//Mono<List<ReviewDto>>
			.flatMap(movie->this.util.succesResponse(HttpStatus.OK, 
						"Review returned", movie)
						)
			.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
	@PostMapping(value="/{movieId}/reviews")
	Mono<ResponseEntity<RestResponse>> saveReview(@PathVariable String movieId,@Valid @RequestBody Mono<ReviewDto> reviewDto)
	{	
	return reviewDto
			.flatMap(dto->{
				dto.setMovieId(movieId);
				return this.reviewService.saveReview(dto);
			})
			.flatMap(movie->this.util.succesResponse(HttpStatus.CREATED, 
						"Review saved", movie)
						)
			.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
	@PutMapping(value="/{movieId}/reviews/{reviewId}")
	Mono<ResponseEntity<RestResponse>> updateReview(@PathVariable String movieId,@PathVariable String reviewId,@Valid @RequestBody Mono<ReviewDto> reviewDto)
	{	
	return reviewDto
			.flatMap(dto->{
				dto.setMovieId(movieId);
				dto.setId(reviewId);
				return this.reviewService.updateReview(dto);
			})
			.flatMap(movie->this.util.succesResponse(HttpStatus.OK, 
						"Review saved", movie)
						)
			.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
	@DeleteMapping(value="/reviews/{reviewId}")
	Mono<ResponseEntity<RestResponse>> deleteReviewById(@PathVariable String reviewId)
	{
		return  this.reviewService.deleteReviewById(reviewId)
				.flatMap(movie->this.util.succesResponse(HttpStatus.OK, 
						"Review deleted", movie)
						)
				.onErrorResume(err->this.util.errorResponse(HttpStatus.BAD_REQUEST, err.getMessage(), err.getLocalizedMessage()));
					
	}
}
