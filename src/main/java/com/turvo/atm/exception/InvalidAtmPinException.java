package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAtmPinException extends RuntimeException {
	
	private static final long serialVersionUID = 3408689234670059316L;

	public InvalidAtmPinException(String errorMessage) {
		super(errorMessage);
	}

}
