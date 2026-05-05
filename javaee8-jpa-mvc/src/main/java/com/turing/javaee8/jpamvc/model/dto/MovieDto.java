package com.turing.javaee8.jpamvc.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.Comment;
import com.turing.javaee8.jpamvc.model.Director;
import com.turing.javaee8.jpamvc.model.MovieDetails;


import lombok.Data;

@Data
public class MovieDto {

	String title;
	
	Integer year;

	String genre;
	
	MovieDetailsDto details;
	
	//List<Comment> comments = new ArrayList<>();
	
	
	//private Set<Actor> actors = new HashSet<>();

	//private Set<Director> directors = new HashSet<>();

}
