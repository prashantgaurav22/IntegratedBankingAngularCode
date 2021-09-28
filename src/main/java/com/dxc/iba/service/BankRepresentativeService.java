package com.dxc.iba.service;

import java.util.List;

import com.dxc.iba.entity.BankRepresentative;
import com.dxc.iba.exception.BankRepresentativeException;


public interface BankRepresentativeService {
	
	BankRepresentative add(BankRepresentative bankRepresentative) throws BankRepresentativeException;
	BankRepresentative update(BankRepresentative bankRepresentative) throws BankRepresentativeException;
	boolean deleteById(String representative_Id) throws BankRepresentativeException;
	BankRepresentative getById(String representative_Id);
	List<BankRepresentative> getAllBankRepresentative();

}
