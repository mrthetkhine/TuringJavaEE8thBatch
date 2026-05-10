package com.turing.javaee8.jpamvc.model.dto;

import java.util.Date;
import com.turing.javaee8.jpamvc.model.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorDto {
	Long id;
	
	@NotBlank(message = "{required.human.firstName}")
	@Size(min = 3, max = 100, message = "{size.human.firstName}")
	String firstName;
	
	@NotBlank(message = "{required.human.lastName}")
	String lastName;
	
	String fullName;
	
	@NotNull(message = "{required.human.gender}")
	Gender gender;

	@NotNull(message = "{required.human.birthday}")
	Date birthday;
	
	Integer age;
}
