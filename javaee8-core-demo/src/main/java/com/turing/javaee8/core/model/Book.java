package com.turing.javaee8.core.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Book {
	@NotNull
	@Size(min=5, message="Author must be at least 5 characters long")
	String author;
	
	@NotNull
	@Size(min=5, message="Title must be at least 5 characters long")
	String title;
	
	int year;
	
	public Book(String author, String title, int year) {
		super();
		this.author = author;
		this.title = title;
		this.year = year;
	}
	
}
