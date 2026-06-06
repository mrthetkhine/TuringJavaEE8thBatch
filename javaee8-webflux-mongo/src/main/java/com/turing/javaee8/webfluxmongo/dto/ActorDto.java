package com.turing.javaee8.webfluxmongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ActorDto extends BaseDto {
	
	
	@NotBlank(message="{required.human.firstName}")
	String firstName;
	
	@NotBlank(message="{required.human.lastName}")
	String lastName;
	
	@NotBlank(message="{required.human.gender}")
	String gender;
}
