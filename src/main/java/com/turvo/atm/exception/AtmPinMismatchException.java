package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtmPinMismatchException extends RuntimeException {
	
	private static final long serialVersionUID = -394492013226967738L;

	public AtmPinMismatchException(String errorMessage) {
		super(errorMessage);
	}
	
}
