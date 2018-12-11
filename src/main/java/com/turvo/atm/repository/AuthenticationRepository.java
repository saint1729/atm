package com.turvo.atm.repository;

import org.springframework.data.repository.CrudRepository;

import com.turvo.atm.model.Account;

public interface AuthenticationRepository extends CrudRepository<Account, Long> {
	
	Account findByUserName(String userName);
	
}
