package com.turing.javaee8.jpamvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
@Entity
public class MovieDetails extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "movie_id")
	Movie movie;
	
	@Column
	String details;
}
