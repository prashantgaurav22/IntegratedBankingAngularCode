package com.dxc.iba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.iba.entity.Account;
import com.dxc.iba.entity.Customer;
import com.dxc.iba.exception.AccountException;
import com.dxc.iba.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	public static int accountId = 12341;

	@Autowired
	private AccountRepository accRepo;

	public void addDetails() throws AccountException {
		Account acc = new Account();
		acc.setAccountNumber(accountId++);
		acc.setAccountType("savings");
		acc.setTransactions("cys");
		acc.setBalance(0);
		acc.setIfscCode("wadwa");
		this.add(acc);

	}

	@Transactional
	@Override
	public Account add(Account account) throws AccountException {
		
		
		if (account != null) {
			if (accRepo.existsById(account.getAccountNumber())) {
				throw new AccountException(
						"An account with the accountNumber " + account.getAccountNumber() + " already exists!");
			}

			account.setAccountNumber(accountId=accountId+1);
			accRepo.save(account);
		}
		return account;
	}

	@Override
	public Account update(Account account) throws AccountException {

		if (account != null) {
			if (!accRepo.existsById(account.getAccountNumber())) {
				throw new AccountException("No account Found To Update!");
			}
			
			accRepo.save(account);
		}
		return account;
	}

	@Override
	public boolean deleteById(Integer accountNumber) throws AccountException {

		boolean deleted = false;
		if (!accRepo.existsById(accountNumber)) {
			throw new AccountException("No Such account Found To Delete!");
		}

		accRepo.deleteById(accountNumber);
		deleted = true;

		return deleted;
	}

	@Override
	public Account getById(Integer accountNumber) {
		return accRepo.findById(accountNumber).orElse(null);

	}

	@Override
	public List<Account> getAllAccounts() {
		return accRepo.findAll();

	}

}
