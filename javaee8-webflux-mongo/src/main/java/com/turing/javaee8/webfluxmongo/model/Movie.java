package com.turing.javaee8.webfluxmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Document("movies")
@Data
public class Movie {
	@Id
    private String id;
	
	String tile;
	Integer year;
	String director;
	
	ArrayList<String> generes = new ArrayList<>();
	
	//Embedded model
	MovieDetails details;
	
	 //Reference model
    @DBRef
    private Set<Actor> actors = new HashSet<>();
}
