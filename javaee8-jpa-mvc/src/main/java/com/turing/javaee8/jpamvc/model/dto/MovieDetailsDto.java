package com.turing.javaee8.jpamvc.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MovieDetailsDto {
	
	Long id;
	
	String details;
}
