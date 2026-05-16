package com.turing.javaee8.jpamvc.controller.api.exception;

public class InvalidBusinessLogicException extends RuntimeException{
	public InvalidBusinessLogicException(String message)
	{
		super(message);
	}
}
