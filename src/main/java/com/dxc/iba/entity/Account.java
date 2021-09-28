package com.dxc.iba.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class Account implements Serializable {

	@Id
	@Column(name = "accountNumber",nullable=true)
	@NotNull(message = "accountNumber can not be blank")
	private Integer accountNumber;

	
	
	@Column(name = "accountType")
	@NotNull(message = "accountType can not be blank")
	private String accountType;

	@Column(name = "transactions",nullable = true)
	@NotNull(message = "transactions can not be blank")
	private String transactions;

	@Column(name = "balance")
	@NotNull(message = "balance can not be blank")	
	@Max(value = 250000, message = "balance is expected not more than 250000")
	private double balance;

	@Column(name = "ifscCode")
	@NotNull(message = "ifscCode can not be blank")
	private String ifscCode;

	public Account() {
		// left unimplemented
	}
	
	

	public Account(@NotNull(message = "accountNumber can not be blank") Integer accountNumber,
			@NotNull(message = "accountType can not be blank") String accountType,
			@NotNull(message = "transactions can not be blank") String transactions,
			@NotNull(message = "balance can not be blank") @Max(value = 250000, message = "balance is expected not more than 250000") double balance,
			@NotNull(message = "ifscCode can not be blank") String ifscCode) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.transactions = transactions;
		this.balance = balance;
		this.ifscCode = ifscCode;
	}



	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getTransactions() {
		return transactions;
	}

	public void setTransactions(String transactions) {
		this.transactions = transactions;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", transactions="
				+ transactions + ", balance=" + balance + ", ifscCode=" + ifscCode + "]";
	}

}
