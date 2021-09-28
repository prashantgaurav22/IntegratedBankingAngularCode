package com.dxc.iba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.iba.entity.Beneficiary;
import com.dxc.iba.exception.BeneficiaryException;
import com.dxc.iba.repo.BeneficiaryRepository;


@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	
	@Autowired
	private BeneficiaryRepository beneRepo;

	
	@Transactional
	@Override
	public Beneficiary update(Beneficiary beneficiary) throws BeneficiaryException {
	if(beneficiary!=null) {
		if(!beneRepo.existsById(beneficiary.getAccount_Number())) {
			throw new BeneficiaryException("No Beneficiary Found To Update!");
		}
		
		beneRepo.save(beneficiary);
	}
	return beneficiary;
}

		
	@Transactional
	@Override
	public boolean deleteById(Integer account_Number) throws BeneficiaryException {
		boolean deleted=false;
		if(!beneRepo.existsById(account_Number)) {
			throw new BeneficiaryException("No Such Beneficiary Found To Delete!");
		}
		
		beneRepo.deleteById(account_Number);
		deleted=true;
		
		return deleted;
	}
	
	
	@Override
	public Beneficiary getById(Integer account_Number) {
		return beneRepo.findById(account_Number).orElse(null);
	}

	@Override
	public List<Beneficiary> getAllBeneficiary() {
		return beneRepo.findAll();
	}


	@Override
	public Beneficiary add(Beneficiary beneficiary) throws BeneficiaryException {
		if(beneficiary!=null) {
			if(beneRepo.existsById(beneficiary.getAccount_Number())) {
				throw new BeneficiaryException("An beneficiary with the account_Number "+beneficiary.getAccount_Number()+" already exists!");
			}
			
			beneRepo.save(beneficiary);
		}
		return beneficiary;
	}
	}


