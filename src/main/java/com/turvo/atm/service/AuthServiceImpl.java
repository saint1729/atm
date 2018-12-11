package com.turvo.atm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.atm.model.Account;
import com.turvo.atm.repository.AuthenticationRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private static Map<Long, String> map = new HashMap<>();
	
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	public String generateToken(Account account) {
		Account outputAccount = authenticationRepository.findByUserName(account.getUserName());
		if((account != null) && (outputAccount != null) && (account.getPin().equals(outputAccount.getPin()))) {
			String token = UUID.randomUUID().toString();
			map.put(new Long(outputAccount.getId()), token);
			return token;
		} else {
			throw new RuntimeException("Invalid username or wrong PIN entered. Retry!");
		}
	}
	
	public boolean verifyToken(Long accountId, String token) {
		
		String tokenValue = map.get(accountId);
		if(tokenValue != null) {
			if(tokenValue.equals(token)) {
				map.remove(accountId);
				return true;
			}
		}

		return false;
	}
	
}
