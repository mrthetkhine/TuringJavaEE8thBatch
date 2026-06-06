package com.turing.javaee8.webfluxmongo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BaseDto {
	String id;
	Date createdAt;
}
