package com.turvo.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.turvo.atm.model.Account;
import com.turvo.atm.service.AccountService;


@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateFullName/{token}/{userName}/{fullName}", method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> updateFullName(@PathVariable("token") String token,
			@PathVariable("userName") String userName, @PathVariable("fullName") String newFullName) {
		return new ResponseEntity<Account>(accountService.updateFullName(token, userName, newFullName), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateEmailAddress/{token}/{userName}/{emailAddress}", method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> updateEmailAddress(@PathVariable("token") String token,
			@PathVariable("userName") String userName, @PathVariable("emailAddress") String emailAddress) {
		return new ResponseEntity<Account>(accountService.updateEmailAddress(token, userName, emailAddress), HttpStatus.OK);
	}


}
