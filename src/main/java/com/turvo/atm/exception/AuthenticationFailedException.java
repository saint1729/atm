package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationFailedException extends RuntimeException {

	private static final long serialVersionUID = 5470791557967944856L;
	
	public AuthenticationFailedException(String errorMessage) {
		super(errorMessage);
	}

}
