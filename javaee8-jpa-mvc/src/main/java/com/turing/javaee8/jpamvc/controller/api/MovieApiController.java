package com.turing.javaee8.jpamvc.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.jpamvc.controller.api.common.ApiResponse;
import com.turing.javaee8.jpamvc.controller.api.common.ApiSuccessResponse;
import com.turing.javaee8.jpamvc.controller.api.common.ApiUtil;
import com.turing.javaee8.jpamvc.controller.api.common.SuccessCode;
import com.turing.javaee8.jpamvc.controller.api.exception.BeanValidationException;
import com.turing.javaee8.jpamvc.controller.api.exception.ResourceNotFoundException;
import com.turing.javaee8.jpamvc.model.dto.ActorDto;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;
import com.turing.javaee8.jpamvc.service.MovieService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.turing.javaee8.jpamvc.controller.api.common.*; 
@Slf4j
@RestController
@RequestMapping(value="api/movies")
public class MovieApiController {

	@Autowired
	MovieService movieService;
	
	@Autowired
	ApiUtil apiUtil;
	
	@GetMapping
	ResponseEntity<ApiSuccessResponse<List<MovieDto>>> getAllMovies()
	{
		List<MovieDto> movies = this.movieService.getAllMovie();
		log.info("Movie length "+movies.size());
		
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Movie list ok",movies);
	}
	@GetMapping(value="/{id}")
	ResponseEntity<ApiSuccessResponse<MovieDto>> getAllMovieById(@PathVariable Long id)
	{
		log.info("Movie get by id "+id);
		Optional<MovieDto> movie = this.movieService.getMovieById(id);
		
		return movie.map(m->this.apiUtil.buildSucessResponse(HttpStatus.OK,
															SuccessCode.SUCESS.toString(),
															"Movie "+id+" returned",m))
				     .orElseThrow(()-> new ResourceNotFoundException("Movie "+id + " not found"));
		
	}
	@PostMapping
	ResponseEntity<ApiSuccessResponse<MovieDto>> createMovie(@Valid @RequestBody MovieDto movieDto, BindingResult bindingResult) throws BeanValidationException
	{
		log.info("createMovie dto "+ movieDto);
		if(bindingResult.hasErrors())
		{
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
		else
		{
			MovieDto savedMovie = this.movieService.saveMovie(movieDto);
			return this.apiUtil.buildSucessResponse(HttpStatus.CREATED,
					SuccessCode.SUCESS.toString(),
					"Movie "+savedMovie+" created",savedMovie);
		}
	}
	@PutMapping(value="/{id}")
	ResponseEntity<ApiSuccessResponse<MovieDto>> updateMovie(@PathVariable Long id,@Valid @RequestBody MovieDto movieDto, BindingResult bindingResult) throws BeanValidationException
	{
		movieDto.setId(id);
		log.info("update movie dto "+ movieDto);
		if(bindingResult.hasErrors())
		{
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
		else
		{
			MovieDto updateMovie = this.movieService.updateMovie(movieDto);
			return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Movie "+updateMovie+" updated",updateMovie);
		}
	}
	@DeleteMapping(value="/{id}")
	ResponseEntity<ApiSuccessResponse<MovieDto>> deleteMovieById(@PathVariable Long id)
	{
		log.info("Delete movie by id "+id);
		MovieDto deletedMovie = this.movieService.deleteMovieById(id);
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
				SuccessCode.SUCESS.toString(),
				"Movie "+deletedMovie.getId()+" deleted",deletedMovie);
		
	}
	@GetMapping(value="/{id}/actors")
	ResponseEntity<ApiSuccessResponse<List<ActorDto>>> getAllActorInMovie(@PathVariable Long id)
	{
		log.info("getAllActorInMovie id "+id);
		List<ActorDto> actors = this.movieService.getAllActorInMovie(id);
		
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
												SuccessCode.SUCESS.toString(),
												"Movie "+id+" returned",actors);
		
	}
	@PostMapping(value="/{movieId}/assignment/actors/{actorId}")
	ResponseEntity<ApiSuccessResponse<MovieDto>> assignActorToMovie(@PathVariable Long movieId,@PathVariable Long actorId)
	{
		log.info("assignActorToMovie ");
		MovieDto movie = this.movieService.assignActorToMovie(movieId, actorId);
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
				SuccessCode.SUCESS.toString(),
				"Actor assigned to movie",movie);
	}
}
