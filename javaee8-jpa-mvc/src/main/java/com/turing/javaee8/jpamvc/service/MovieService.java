package com.turing.javaee8.jpamvc.service;
import java.util.List;
import java.util.Optional;

import com.turing.javaee8.jpamvc.model.dto.*;

public interface MovieService {
	List<MovieDto> getAllMovie();
	Optional<MovieDto> getMovieById(Long id);
	MovieDto saveMovie(MovieDto dto);
	MovieDto updateMovie(MovieDto dto);
	MovieDto deleteMovieById(Long id);
	List<ActorDto> getAllActorInMovie(Long movieId);
	MovieDto assignActorToMovie(Long movieId,Long actorId);
}
