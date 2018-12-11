package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateAccountException extends RuntimeException {

	private static final long serialVersionUID = 4087497114084482358L;
	
	public DuplicateAccountException(String errorMessage) {
		super(errorMessage);
	}

}
