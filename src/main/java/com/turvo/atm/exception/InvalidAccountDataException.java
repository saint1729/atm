package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAccountDataException extends RuntimeException {
	
	private static final long serialVersionUID = 3336352225237189063L;

	public InvalidAccountDataException(String errorMessage) {
		super(errorMessage);
	}

}
