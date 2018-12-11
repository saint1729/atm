package com.turvo.atm.service;

import com.turvo.atm.model.Account;

public interface AccountService {

	public Account createAccount(Account account);
	public Account updateFullName(String token, String userName, String newFullName);
	public Account updateEmailAddress(String token, String userName, String emailAddress);
}
