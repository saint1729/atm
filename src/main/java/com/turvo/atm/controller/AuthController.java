package com.turvo.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.atm.model.Account;
import com.turvo.atm.service.AuthService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generateToken(@RequestBody Account account) {
		try {
			return new ResponseEntity<String>(authService.generateToken(account), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}