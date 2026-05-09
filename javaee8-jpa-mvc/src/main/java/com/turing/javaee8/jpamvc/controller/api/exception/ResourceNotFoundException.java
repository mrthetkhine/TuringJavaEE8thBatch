package com.turing.javaee8.jpamvc.controller.api.exception;

public class ResourceNotFoundException extends RuntimeException{

	String message;
	public ResourceNotFoundException(String message)
	{
		super(message);
		this.message = message;
	}
}
