package com.turing.javaee8.jpamvc.model.dto;

import lombok.Data;

@Data
public class TodoDto {
	Long id;
	String title;
	boolean completed;
}
