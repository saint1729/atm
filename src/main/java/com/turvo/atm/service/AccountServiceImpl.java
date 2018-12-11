package com.turvo.atm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.atm.model.Account;
import com.turvo.atm.repository.AccountRepository;
import com.turvo.atm.repository.AuthenticationRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AuthenticationRepository authenticationRepository;
	@Autowired
	AuthService authService;
	
	private Lock lock = new ReentrantLock();
	
	public Account createAccount(Account account) {
		
		Long id = account.getId();
		String userName = account.getUserName();
		String fullName = account.getFullName();
		Long accountNumber = account.getAccountNumber();
		String userEmail = account.getEmailId();
		Integer pin = account.getPin();
		Double balance = account.getBalance();
		
		if((userName == null) || (fullName == null) || (userEmail == null) || (pin == null)
				|| (accountNumber != null) || (balance != null) || (id != null)) {
			throw new RuntimeException("Enter only User Name, Full Name, Email ID and PIN");
		} else if((pin < 0) || (pin > 9999)) {
			throw new RuntimeException("Enter a valid 4 digit PIN");
		}
		
		
		lock.lock();
		try {
			List<Account> accounts = accountRepository.findAll();
			Set<Account> set = new HashSet<>(accounts);
			
			if(set.contains(account)) {
				throw new RuntimeException("Username already exists. Choose a different one.");
			}
			
			Random random = new Random();
			
			do {
				accountNumber = (long)(random.nextInt(10000000-1000000))+1000000L;
				account.setAccountNumber(accountNumber);
			} while(set.contains(account));
			
			account.setBalance(0.0);
			Account savedAccount = accountRepository.save(account);
			return savedAccount;
		} finally {
			lock.unlock();
		}
	}
	
	public Account updateFullName(String token, String userName, String newFullName) {
		Account account = authenticationRepository.findByUserName(userName);
		
		if((account != null) && authService.verifyToken(account.getId(), token)) {
			account.setFullName(newFullName);
			Account savedAccount = accountRepository.save(account);
			return savedAccount;
		} else {
			throw new RuntimeException("Account not found for user with user name " + userName + " or token authentication failed. Try again.");
		}
	}
	
	public Account updateEmailAddress(String token, String userName, String emailAddress) {
		Account account = authenticationRepository.findByUserName(userName);
		
		if((account != null) && authService.verifyToken(account.getId(), token)) {
			account.setEmailId(emailAddress);;
			Account savedAccount = accountRepository.save(account);
			return savedAccount;
		} else {
			throw new RuntimeException("Account not found for user with user name " + userName + " or token authentication failed. Try again.");
		}
	}
	
}
