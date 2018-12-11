package com.turvo.atm.service;

import com.turvo.atm.model.Account;

public interface AtmService {
	
	public Double getBalance(String token, String userName);
	public Account withdrawBalance(String token, String userName, Double money);
	public Account depositBalance(String token, String userName, Double money);
	
}
