package com.turing.javaee8.jpamvc.model;

import java.util.Date;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Human extends BaseEntity{
	@Column
	String firstName;
	
	@Column
	String lastName;
	
	@Formula(value = "concat(first_name,' ',last_name)")
	private String fullName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column
	Gender gender;
	
	@Column
	Date birthday;
	
	@Formula(value = "TIMESTAMPDIFF(YEAR,birthday,CURDATE())")
	Integer age;
}
