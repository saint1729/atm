package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6656225864300703362L;
	
	public AccountNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
