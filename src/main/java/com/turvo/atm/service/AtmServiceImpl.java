package com.turvo.atm.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.atm.model.Account;
import com.turvo.atm.repository.AccountRepository;
import com.turvo.atm.repository.AuthenticationRepository;

@Service
public class AtmServiceImpl implements AtmService {
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AuthenticationRepository authenticationRepository;
	@Autowired
	AuthService authService;
	
	private Lock lock = new ReentrantLock();
	
	public Double getBalance(String token, String userName) {
		
		Account account = authenticationRepository.findByUserName(userName);
		
		if((account != null) && authService.verifyToken(account.getId(), token)) {
			return account.getBalance();
		}
		
		throw new RuntimeException("Invalid account or token authentication failed");
	}

	public Account withdrawBalance(String token, String userName, Double money) {
		
		Account account = authenticationRepository.findByUserName(userName);

		if((account != null) && authService.verifyToken(account.getId(), token)) {
			double balance = account.getBalance();
			
			if(balance < money) {
				throw new RuntimeException("Low balance");
			}
			
			Account savedAccount = null;
			
			lock.lock();
			try {
				account.setBalance(balance-money);
				savedAccount = accountRepository.save(account);
			} finally {
				lock.unlock();
			}
			
			return savedAccount;
		}
		
		throw new RuntimeException("Invalid account or token authentication failed");
	}

	public Account depositBalance(String token, String userName, Double money) {
		
		Account account = authenticationRepository.findByUserName(userName);

		if((account != null) && authService.verifyToken(account.getId(), token)) {
			double balance = account.getBalance();
			
			Account savedAccount = null;
			
			lock.lock();
			try {
				account.setBalance(balance+money);
				savedAccount = accountRepository.save(account);
			} finally {
				lock.unlock();
			}
			
			return savedAccount;
		}

		throw new RuntimeException("Invalid account or token authentication failed");
	}
	
	
}
