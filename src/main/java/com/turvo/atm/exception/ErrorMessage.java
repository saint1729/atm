package com.turvo.atm.exception;

public enum ErrorMessage {
	
	
	ACCOUNT_NOT_FOUND("Account not found with the entered username."),
	AUTHENTICATION_FAILED("Token authentication failed. Generated a new token and resubmit the request."),
	DUPLICATE_ACCOUNT("An account with that username already exists. Try creating with a new username."),
	INVALID_ATM_PIN("Entered ATM pin is invalid. Entered a valid 4 digit positive ATM pin."),
	INVALID_DATA_ENTERED("Enter only User Name, Full Name, Email ID and PIN in payload to successfully create an account.");
	
	private String errorMessage;

	private ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
}
