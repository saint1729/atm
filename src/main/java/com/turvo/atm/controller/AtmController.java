package com.turvo.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.atm.model.Account;
import com.turvo.atm.service.AtmService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/atm")
public class AtmController {
	
	@Autowired
	private AtmService atmService;
	
	@RequestMapping(value = "/getBalance/{token}/{userName}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getBalanceById(@PathVariable("token") String token, @PathVariable("userName") String userName) {
		return new ResponseEntity<Double>(atmService.getBalance(token, userName), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deposit/{token}/{userName}/{amount}", method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> depositAmount(@PathVariable("token") String token,
			@PathVariable("userName") String userName, @PathVariable("amount") double money) {
		try {
			return new ResponseEntity<Object>(atmService.depositBalance(token, userName, money), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/withdraw/{token}/{userName}/{amount}", method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> withdrawAmount(@PathVariable("token") String token,
			@PathVariable("userName") String userName, @PathVariable("amount") double money) {
		return new ResponseEntity<Account>(atmService.withdrawBalance(token, userName, money), HttpStatus.OK);
	}
	
	
}
