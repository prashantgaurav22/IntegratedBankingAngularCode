package com.dxc.iba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Entity
@Table(name="beneficiaries")
public class Beneficiary {
	
	@Id
	@Column(name="acc")
	private Integer account_Number;

	@Column(name = "fname", nullable = false)
	@NotBlank(message = "first name can not be blank")
	private String fname;
	
	
	@Column(name = "lname", nullable = false)
	@NotBlank(message = "last name can not be blank")
	private String lname;
	
	@Column(name = "phoneNum", nullable = false)
	@NotNull(message = "phone number can not be blank")
	private String phoneNumber;
	
	@Column(name = "ifsc", nullable = false)
	@NotBlank(message = "IFSC code  can not be blank")
	private String ifsc;
	
	
	@Column(name = "transfertype", nullable = false)
	@NotBlank(message = "Transfer type can not be blank")
	private String transfer_Type;
	
	public Beneficiary() {
		//left unimplemented
	}

	public Integer getAccount_Number() {
		return account_Number;
	}

	public void setAccount_Number(Integer account_Number) {
		this.account_Number = account_Number;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getTransfer_Type() {
		return transfer_Type;
	}

	public void setTransfer_Type(String transfer_Type) {
		this.transfer_Type = transfer_Type;
	}

	public Beneficiary(Integer account_Number, @NotBlank(message = "first name can not be blank") String fname,
			@NotBlank(message = "last name can not be blank") String lname,
			@NotNull(message = "phone number can not be blank") String phoneNumber,
			@NotBlank(message = "IFSC code  can not be blank") String ifsc,
			@NotBlank(message = "Transfer type can not be blank") String transfer_Type) {
		super();
		this.account_Number = account_Number;
		this.fname = fname;
		this.lname = lname;
		this.phoneNumber = phoneNumber;
		this.ifsc = ifsc;
		
		this.transfer_Type = transfer_Type;
	}
}