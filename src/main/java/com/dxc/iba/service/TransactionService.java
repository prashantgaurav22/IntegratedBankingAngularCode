package com.dxc.iba.service;

import java.util.List;

import com.dxc.iba.entity.Transaction;
import com.dxc.iba.exception.TransactionException;

public interface TransactionService {

	 Transaction add( Transaction transaction) throws  TransactionException;

	 Transaction update( Transaction transaction) throws  TransactionException;

	boolean deleteById(Integer transactionId) throws  TransactionException;

	 Transaction getById(Integer transactionId);

	List<Transaction> getAllTransactions();

}
