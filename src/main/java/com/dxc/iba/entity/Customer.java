package com.dxc.iba.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Embeddable
@Entity
@Table(name = "customers", uniqueConstraints = { @UniqueConstraint(columnNames = { "aadharNumber" }) })
public class Customer implements Serializable {

	@Id
	@Column(name = "custId", nullable = false)
	private Integer customerId;

	@Column(name = "account", nullable = true)
	private int account;

	public int getAccount() {
		return account;
	}

	public void setAccount(int id) {
		this.account = id;
	}

	@Column(name = "aadharNumber", unique = true, nullable = false)
	@NotNull(message = "aadhar number can not be blank")
	private Integer aadharNumber;

	@Column(name = "fname", nullable = false)
	@NotBlank(message = "first name can not be blank")
	private String fName;

	@Column(name = "lname", nullable = false)
	@NotBlank(message = "last name can not be blank")
	private String lName;

	@Column(name = "email", nullable = false)
	@NotBlank(message = "Email can not be blank")
	private String email;

	@Column(name = "password", nullable = false)
	@NotBlank(message = "password can not be blank")
	private String password;

	@Column(name = "DOB", nullable = true)
	@NotNull(message = "date of birth can not be blank")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataOfBirth;

	@Column(name = "phoneNum", nullable = false)
	@NotNull(message = "phone number can not be blank")
	private String phoneNumber;

	@Column(name = "address", nullable = false)
	@NotNull(message = "address can not be blank")
	private String address;

	@Column(name = "panCard", nullable = true)
	private String panCard;

	@Column(name = "gender", nullable = false)
	@NotBlank(message = "gender can not be blank")
	private String gender;

	@Column(name = "accounttype", nullable = false)
	@NotBlank(message = "Account type can not be blank")
	private String accountType;

	@Override
	public String toString() {
		return "Customer [aadharNumber=" + aadharNumber + ", customerId=" + customerId + ", fName=" + fName + ", lName="
				+ lName + ", email=" + email + ", password=" + password + ", dataOfBirth=" + dataOfBirth
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", panCard=" + panCard + ", gender="
				+ gender + ", accountType=" + accountType + "]";
	}

	public Integer getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(Integer aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDataOfBirth() {
		return dataOfBirth;
	}

	public void setDataOfBirth(LocalDate dataOfBirth) {
		this.dataOfBirth = dataOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Customer(@NotNull(message = "aadhar number can not be blank") Integer aadharNumber, Integer customerId,
			@NotBlank(message = "first name can not be blank") String fName,
			@NotBlank(message = "last name can not be blank") String lName,
			@NotBlank(message = "Email can not be blank") String email,
			@NotBlank(message = "password can not be blank") String password,
			@NotNull(message = "date of birth can not be blank") LocalDate dataOfBirth,
			@NotNull(message = "phone number can not be blank") String phoneNumber,
			@NotNull(message = "address can not be blank") String address, String panCard,
			@NotBlank(message = "gender can not be blank") String gender,
			@NotBlank(message = "Account type can not be blank") String accountType) {
		super();
		this.aadharNumber = aadharNumber;
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.dataOfBirth = dataOfBirth;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.panCard = panCard;
		this.gender = gender;
		this.accountType = accountType;
	}

	public Customer() {

	}

}