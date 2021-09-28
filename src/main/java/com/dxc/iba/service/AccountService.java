package com.dxc.iba.service;

import java.util.List;

import com.dxc.iba.entity.Account;
import com.dxc.iba.exception.AccountException;

public interface AccountService {

	Account add(Account account) throws AccountException;

	Account update(Account account) throws AccountException;

	boolean deleteById(Integer accountNumber) throws AccountException;

	Account getById(Integer accountNumber);

	List<Account> getAllAccounts();
}
