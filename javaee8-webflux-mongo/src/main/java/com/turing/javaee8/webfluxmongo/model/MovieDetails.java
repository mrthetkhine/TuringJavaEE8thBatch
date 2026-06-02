package com.turing.javaee8.webfluxmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.Data;

@Data
//@Document("movie_details")
public class MovieDetails {

	String details;
}
