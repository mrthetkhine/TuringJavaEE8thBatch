package com.turing.javaee8.jpamvc.bean;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.MovieDetails;
import com.turing.javaee8.jpamvc.model.dto.MovieDetailsDto;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Mapper {

	ModelMapper modelMapper = new ModelMapper();
	public Mapper()
	{
		this.modelMapper.getConfiguration()
		 .setFieldMatchingEnabled(true)
	      .setFieldAccessLevel(AccessLevel.PRIVATE)
	      .setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.typeMap(MovieDetailsDto.class, MovieDetails.class)
			.addMappings(mapper -> {
			 
				mapper.skip(MovieDetails::setMovie);
			});
		
		log.info("Mapper initialized");
	}
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	  	      .stream()
	  	      .map(element -> modelMapper.map(element, targetClass))
	  	      .collect(Collectors.toList());
	}
	public <S, T> List<T> mapSet(Set<S> source, Class<T> targetClass) {
	
	    return source
	  	      .stream()
	  	      .map(element -> modelMapper.map(element, targetClass))
	  	      .collect(Collectors.toList());
	}
	public  <D> D map(Object source, Class<D> destinationType) {
		return this.modelMapper.map(source, destinationType);
	}
	public ModelMapper getMapper()
	{
		return this.modelMapper;
	}
}
