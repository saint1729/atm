package com.turvo.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class EnhancedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleGenericException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<String> handleAccountNotFoundException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuthenticationFailedException.class)
	public final ResponseEntity<String> handleAuthenticationFailedException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DuplicateAccountException.class)
	public final ResponseEntity<String> handleDuplicateAccountException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidAccountDataException.class)
	public final ResponseEntity<String> handleInvalidAccountDataException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidAtmPinException.class)
	public final ResponseEntity<String> handleInvalidAtmPinException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AtmPinMismatchException.class)
	public final ResponseEntity<String> handleAtmPinMismatchException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AtmPinMatchException.class)
	public final ResponseEntity<String> handleAtmPinMatchException(Exception e, WebRequest request) {
		String errorMessage = e.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
}
