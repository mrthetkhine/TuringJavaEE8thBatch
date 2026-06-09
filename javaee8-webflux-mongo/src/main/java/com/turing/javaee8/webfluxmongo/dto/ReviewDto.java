package com.turing.javaee8.webfluxmongo.dto;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.turing.javaee8.webfluxmongo.model.Movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewDto {

	    String id;
	    String movieId;
		
		@NotBlank(message="movie review should not be blank")
		String review;
		
		@NotNull(message="movie rating should not be blank")
		Integer rating;
}
