package com.dxc.iba.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="cards")
public class Cards {
	
	@Id
	@Column(name="cvv")
	private int cvv;
	
	@Column(name = "name", nullable = false)
	@NotBlank(message = "name can not be blank")
	@Size(min = 3, max = 45, message = "name must be of 3 to 45 chars in length")
	private String name;
	
	@Column(name = "cardNum", nullable = false)
	@NotNull(message = "card number can not be blank")
	@Size(min = 12, max = 16, message = "card number is manditory to be filled")	
	private String card_Number;
	
	@Column(name = "vdate", nullable = true)
	@NotNull(message = "Validation date can not be blank")
	@PastOrPresent(message = "Validation Date can not be a future date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate valid_Date;
	
	@Column(name = "edate", nullable = true)
	@NotNull(message = "Expiry date can not be blank")
	@Future(message = "Expiry Date can not be a future date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate expiry_Date;
	
	@Transient
	private int pin;
	
	public Cards() {
		//left unimplemented
	}

	public Cards(int cvv,
			@NotBlank(message = "name can not be blank") @Size(min = 3, max = 45, message = "name must be of 3 to 45 chars in length") String name,
			@NotNull(message = "card number can not be blank") @Min(10) @Max(25) String card_Number,
			@NotNull(message = "Validation date can not be blank") @PastOrPresent(message = "Validation Date can not be a future date") LocalDate valid_Date,
			@NotNull(message = "Expiry date can not be blank") @Future(message = "Expiry Date can not be a future date") LocalDate expiry_Date,
			int pin) {
		super();
		this.cvv = cvv;
		this.name = name;
		this.card_Number = card_Number;
		this.valid_Date = valid_Date;
		this.expiry_Date = expiry_Date;
		this.pin = pin;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard_Number() {
		return card_Number;
	}

	public void setCard_Number(String card_Number) {
		this.card_Number = card_Number;
	}

	public LocalDate getValid_Date() {
		return valid_Date;
	}

	public void setValid_Date(LocalDate valid_Date) {
		this.valid_Date = valid_Date;
	}

	public LocalDate getExpiry_Date() {
		return expiry_Date;
	}

	public void setExpiry_Date(LocalDate expiry_Date) {
		this.expiry_Date = expiry_Date;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}	

}
