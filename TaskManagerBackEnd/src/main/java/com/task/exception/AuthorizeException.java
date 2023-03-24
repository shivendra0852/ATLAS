package com.task.exception;

public class AuthorizeException extends RuntimeException{
	public AuthorizeException() {
		
	}
	
	public AuthorizeException(String message) {
		super(message);
	}
}
