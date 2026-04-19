package com.turing.javaee8.jpamvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	@Column
	String city;
	
	@Column
	String address;
}
