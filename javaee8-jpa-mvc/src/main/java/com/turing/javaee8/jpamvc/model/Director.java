package com.turing.javaee8.jpamvc.model;

import java.util.Date;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Director extends Human{
	Address address;
	
}
