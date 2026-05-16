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

import com.turing.javaee8.jpamvc.controller.api.common.ApiSuccessResponse;
import com.turing.javaee8.jpamvc.controller.api.common.ApiUtil;
import com.turing.javaee8.jpamvc.controller.api.common.SuccessCode;
import com.turing.javaee8.jpamvc.controller.api.exception.BeanValidationException;
import com.turing.javaee8.jpamvc.controller.api.exception.ResourceNotFoundException;
import com.turing.javaee8.jpamvc.model.dto.ActorDto;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;
import com.turing.javaee8.jpamvc.service.ActorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="api/actors")
public class ActorApiController {

	@Autowired
	ApiUtil apiUtil;
	
	@Autowired
	ActorService actorService;
	
	@GetMapping
	ResponseEntity<ApiSuccessResponse<List<ActorDto>>> getAllActor()
	{
		List<ActorDto> actors = this.actorService.getAllActor();
		log.info("Actor length "+actors.size());
		
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Actors list ok",actors);
	}
	
	@GetMapping(value="{id}")
	ResponseEntity<ApiSuccessResponse<ActorDto>> getActorById(@PathVariable Long id)
	{
		log.info("Get actor by id "+id);
		Optional<ActorDto> actor = this.actorService.getActorById(id);
		
		return actor.map(act->this.apiUtil.buildSucessResponse(HttpStatus.OK,
											SuccessCode.SUCESS.toString(),
											"Actors ok",act))
				.orElseThrow(()->new ResourceNotFoundException("Actor "+id +" not found"));
	
	}
	@PostMapping
	ResponseEntity<ApiSuccessResponse<ActorDto>> saveActor(@Valid @RequestBody  ActorDto actorDto
			,BindingResult bindingResult) throws BeanValidationException
	{
		if(bindingResult.hasErrors())
		{
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
		else
		{
			ActorDto savedActor = this.actorService.saveActor(actorDto);
			return this.apiUtil.buildSucessResponse(HttpStatus.CREATED,
					SuccessCode.SUCESS.toString(),
					"Actor created ok",savedActor);
		}
	
	}
	@PutMapping(value="{id}")
	ResponseEntity<ApiSuccessResponse<ActorDto>> upateActor(@PathVariable Long id,
			@Valid @RequestBody  ActorDto actorDto
			,BindingResult bindingResult) throws BeanValidationException
	{
		actorDto.setId(id);
		if(bindingResult.hasErrors())
		{
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
		else
		{
			ActorDto savedActor = this.actorService.updateActor(actorDto);
			return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Actor updated ok",savedActor);
		}
	
	}
	@DeleteMapping(value="{id}")
	ResponseEntity<ApiSuccessResponse<ActorDto>> deleteActor(@PathVariable Long id) 
	{
		ActorDto deletedActor = this.actorService.deleteActorById(id);
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
				SuccessCode.SUCESS.toString(),
				"Actor deleted ok",deletedActor);
	
	}
}
