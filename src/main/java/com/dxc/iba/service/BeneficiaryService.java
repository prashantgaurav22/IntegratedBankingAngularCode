package com.dxc.iba.service;

import java.util.List;

import com.dxc.iba.entity.Beneficiary;
import com.dxc.iba.exception.BeneficiaryException;

public interface BeneficiaryService {
	
	Beneficiary add(Beneficiary beneficiary) throws BeneficiaryException;
	Beneficiary update(Beneficiary beneficiary) throws BeneficiaryException;
	boolean deleteById(Integer account_Number) throws BeneficiaryException;
	Beneficiary getById(Integer account_Number);
	List<Beneficiary> getAllBeneficiary();

}
