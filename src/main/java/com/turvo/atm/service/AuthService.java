package com.turvo.atm.service;

import com.turvo.atm.model.Account;

public interface AuthService {
	
	public String generateToken(Account account);
	public boolean verifyToken(Long accountId, String token);

}
