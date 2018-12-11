package com.turvo.atm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.turvo.atm.exception.AccountNotFoundException;
import com.turvo.atm.exception.AuthenticationFailedException;
import com.turvo.atm.exception.DuplicateAccountException;
import com.turvo.atm.exception.ErrorMessage;
import com.turvo.atm.exception.InvalidAccountDataException;
import com.turvo.atm.exception.InvalidAtmPinException;
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
			throw new InvalidAccountDataException(ErrorMessage.INVALID_DATA_ENTERED.getErrorMessage());
		} else if((pin < 1000) || (pin > 9999)) {
			throw new InvalidAtmPinException(ErrorMessage.INVALID_ATM_PIN.getErrorMessage());
		}
		
		
		lock.lock();
		try {
			List<Account> accounts = accountRepository.findAll();
			Set<Account> set = new HashSet<>(accounts);
			
			if(set.contains(account)) {
				throw new DuplicateAccountException(ErrorMessage.DUPLICATE_ACCOUNT.getErrorMessage());
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

		if(account == null) {
			throw new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND.getErrorMessage());
		} else {
			if(authService.verifyToken(account.getId(), token)) {
				account.setFullName(newFullName);
				Account savedAccount = accountRepository.save(account);
				return savedAccount;
			} else {
				throw new AuthenticationFailedException(ErrorMessage.AUTHENTICATION_FAILED.getErrorMessage());
			}
		}
	}
	
	public Account updateEmailAddress(String token, String userName, String emailAddress) {
		Account account = authenticationRepository.findByUserName(userName);
		
		if(account == null) {
			throw new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND.getErrorMessage());
		} else {
			if(authService.verifyToken(account.getId(), token)) {
				account.setEmailId(emailAddress);;
				Account savedAccount = accountRepository.save(account);
				return savedAccount;
			} else {
				throw new AuthenticationFailedException(ErrorMessage.AUTHENTICATION_FAILED.getErrorMessage());
			}
		}
	}
	
}
