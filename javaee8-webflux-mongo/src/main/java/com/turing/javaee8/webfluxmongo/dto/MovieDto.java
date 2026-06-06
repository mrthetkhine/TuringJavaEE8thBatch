package com.turing.javaee8.webfluxmongo.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.model.MovieDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieDto extends BaseDto{
    @NotBlank(message="{required.movie.title}")
	@Size(max = 140)
	String title;
    
    @NotNull(message="{required.movie.year}")
	Integer year;
    
    @NotBlank(message="{required.movie.director}")
	String director;
	
    @NotNull(message="{required.movie.genres}")
	ArrayList<String> genres = new ArrayList<>();

    @NotNull(message="{required.movie.movieDetails}")
	MovieDetailsDto details;
	
	Set<ActorDto> actors = new HashSet<>();
}
