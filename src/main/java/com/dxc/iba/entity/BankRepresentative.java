package com.dxc.iba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="bank_representative")
public class BankRepresentative {
	
	@Id
	@Column(name="repId")
	private String representative_Id;
	
	@Column(name = "name", nullable = false)
	@NotBlank(message = "name can not be blank")
	@Size(min = 3, max = 45, message = "name must be of 3 to 45 chars in length")
	private String name;

	public  BankRepresentative() {
		//left unimplemented
	}

	public BankRepresentative(String representative_Id,
			@NotBlank(message = "name can not be blank") @Size(min = 3, max = 45, message = "name must be of 3 to 45 chars in length") String name) {
		super();
		this.representative_Id = representative_Id;
		this.name = name;
	}

	public String getRepresentative_Id() {
		return representative_Id;
	}

	public void setRepresentative_Id(String representative_Id) {
		this.representative_Id = representative_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
