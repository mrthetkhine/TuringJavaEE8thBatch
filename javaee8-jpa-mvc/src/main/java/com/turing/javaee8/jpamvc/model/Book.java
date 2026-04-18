package com.turing.javaee8.jpamvc.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "books")
public class Book {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column
	String author;

	@Column
	String title;
	
	@Column
	Integer year;
	

	
}
