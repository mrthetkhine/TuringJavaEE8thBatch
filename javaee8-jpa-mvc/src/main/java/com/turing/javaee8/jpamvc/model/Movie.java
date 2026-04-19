package com.turing.javaee8.jpamvc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Movie extends BaseEntity {
	
	@Column
	String title;
	
	@Column
	Integer year;
	
	@Column
	String genre;
	
	@OneToOne(mappedBy = "movie",
			cascade = CascadeType.ALL)	
	MovieDetails details;
}
