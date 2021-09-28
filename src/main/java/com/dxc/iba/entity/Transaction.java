package com.dxc.iba.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

	@Id
	@Column(name = "transactionId")
	@NotNull(message = "transactionId can not be blank")
	private Integer transactionId;
	
	@Column(name = "accountType", nullable = false)
	@NotBlank(message = "accountType can not be blank")
	private String accountType;
	
	@Column(name = "cardNumber", nullable = false)
	@NotNull(message = "cardNumber can not be blank")
	private long cardNumber;
	
	@Column(name = "transactionType", nullable = false)
	@NotBlank(message = "transactionType can not be blank")
	private String transactionType;
	
	@Column(name = "transactiondate", nullable = true)
	@NotNull(message = "date can not be blank")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate transactionDate;
	
	@Column(name = "amount", nullable = false)
	@NotNull(message = "amount can not be blank")
	@Min(value = 5000, message = "amount is expected to be not less than 5000")
	@Max(value = 250000, message = "amount is expected not more than 250000")
	private double amount;
	
	public Transaction() {
		//left unimplemented
	}

	public Transaction(@NotNull(message = "transactionId can not be blank") Integer transactionId, String accountType,
			long cardNumber, String transactionType, LocalDate transactionDate, double amount) {
		super();
		this.transactionId = transactionId;
		this.accountType = accountType;
		this.cardNumber = cardNumber;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountType=" + accountType + ", cardNumber="
				+ cardNumber + ", transactionType=" + transactionType + ", transactionDate=" + transactionDate
				+ ", amount=" + amount + "]";
	}
	
	
	
	
	
	
	
}
