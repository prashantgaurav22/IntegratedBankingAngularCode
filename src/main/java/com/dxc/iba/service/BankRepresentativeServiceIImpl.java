package com.dxc.iba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.iba.entity.BankRepresentative;
import com.dxc.iba.entity.Customer;
import com.dxc.iba.exception.BankRepresentativeException;
import com.dxc.iba.exception.CustomerException;
import com.dxc.iba.repo.BankRepresentativeRepo;


@Service
public class BankRepresentativeServiceIImpl implements BankRepresentativeService {
	
	@Autowired
	private BankRepresentativeRepo bankrepRepo;

	
	@Transactional
	@Override
	public BankRepresentative add(BankRepresentative bankRepresentative) throws BankRepresentativeException {
		if(bankRepresentative!=null) {
			if(bankrepRepo.existsById(bankRepresentative.getRepresentative_Id())) {
				throw new BankRepresentativeException("An employee with the representative Id "+bankRepresentative.getRepresentative_Id()+" already exists!");
			}
			
			bankrepRepo.save(bankRepresentative);
		}
		return bankRepresentative;
	}
	

	@Transactional
	@Override
	public BankRepresentative update(BankRepresentative bankRepresentative) throws BankRepresentativeException {
			if( bankRepresentative!=null) {
				if(!bankrepRepo.existsById(bankRepresentative.getRepresentative_Id())) {
					throw new BankRepresentativeException("No Employee Found To Update!");
				}
				
				bankrepRepo.save(bankRepresentative);
			}
			return bankRepresentative;
		}
	
	@Transactional
	@Override
	public boolean deleteById(String representative_Id) throws BankRepresentativeException {
		boolean deleted=false;
		if(!bankrepRepo.existsById(representative_Id)) {
			throw new BankRepresentativeException("No Such Employee Found To Delete!");
		}
		
		bankrepRepo.deleteById(representative_Id);
		deleted=true;
		
		return deleted;
	}



	@Transactional
	@Override
	public BankRepresentative getById(String representative_Id) {
		return bankrepRepo.findById(representative_Id).orElse(null);
	}

	@Transactional
	@Override
	public List<BankRepresentative> getAllBankRepresentative() {
		return bankrepRepo.findAll();
	}
}

	