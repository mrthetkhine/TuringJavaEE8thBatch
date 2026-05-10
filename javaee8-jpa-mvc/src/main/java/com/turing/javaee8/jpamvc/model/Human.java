package com.turing.javaee8.jpamvc.model;

import java.util.Date;

import org.hibernate.annotations.Formula;

import jakarta.annotation.Generated;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@PostUpdate
	public void updateFormula() {
		this.fullName = this.firstName + " "+this.lastName;
	    log.info("Updated user: " + this);
	}
}
