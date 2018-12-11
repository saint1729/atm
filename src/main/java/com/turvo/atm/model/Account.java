package com.turvo.atm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="Accounts")
public class Account {
	
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("accountHolderName")
	private String fullName;
	@JsonProperty("accountNumber")
	private Long accountNumber;
	@JsonProperty("userEmail")
	private String emailId;
	@JsonProperty("pin")
	private Integer pin;
	@JsonProperty("userBalance")
	private Double balance;
	
	
	public Account() {
		super();
	}
	
	public Account(String userName, String fullName, Long accountNumber, String emailId, Integer pin, Double balance) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.accountNumber = accountNumber;
		this.emailId = emailId;
		this.pin = pin;
		this.balance = balance;
	}

	@JsonIgnore
	@JsonProperty("id")
	public Long getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}
	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}
	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonProperty("accountHolderName")
	public String getFullName() {
		return fullName;
	}
	@JsonProperty("accountHolderName")
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@JsonProperty("accountNumber")
	public Long getAccountNumber() {
		return accountNumber;
	}
	@JsonProperty("userEmail")
	public String getEmailId() {
		return emailId;
	}
	@JsonProperty("userEmail")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@JsonProperty("accountNumber")
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	@JsonProperty("userBalance")
	public Double getBalance() {
		return balance;
	}
	@JsonProperty("userBalance")
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@JsonIgnore
	@JsonProperty("pin")
	public Integer getPin() {
		return pin;
	}
	@JsonIgnore
	@JsonProperty("pin")
	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", fullName=" + fullName + ", accountNumber="
				+ accountNumber + ", emailId=" + emailId + ", pin=" + pin + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Account other = (Account) obj;
		
		if(userName == null) {
			if(other.userName != null) {
				return false;
			}
		} else if(userName.equals(other.userName)) {
			return true;
		}
		
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber)) {
			return false;
		}
		
		return true;
		
	}
	
}
