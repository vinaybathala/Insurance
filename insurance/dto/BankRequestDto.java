package com.insurance.dto;

import javax.validation.constraints.NotEmpty;

public class BankRequestDto {

	@NotEmpty(message = "please enter a valid from account number")
	private String fromAccount;

	@NotEmpty(message = "please enter a valid to account number")
	private String toAccount;
	
	@NotEmpty(message = "please enter a balance")
	private String balance;

	@NotEmpty(message = "please enter a status")
	private String status;

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
