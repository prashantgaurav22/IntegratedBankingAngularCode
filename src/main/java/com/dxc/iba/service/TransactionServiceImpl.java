package com.dxc.iba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.iba.entity.Transaction;
import com.dxc.iba.exception.TransactionException;
import com.dxc.iba.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	public static long accountNo = 123434342;

	@Autowired
	private TransactionRepository tranRepo;

	@Transactional
	@Override
	public Transaction add(Transaction transaction) throws TransactionException {
		if (transaction != null) {
			if (tranRepo.existsById(transaction.getTransactionId())) {
				throw new TransactionException(
						"An Transaction with the transactionId " + transaction.getTransactionId() + " already exists!");
			}

			tranRepo.save(transaction);
		}
		return transaction;
	}
	

	@Override
	public Transaction update(Transaction transaction) throws TransactionException {
	
		if (transaction != null) {
			if (!tranRepo.existsById(transaction.getTransactionId())) {
				throw new TransactionException("No transaction Found To Update!");
			}

			tranRepo.save(transaction);
		}
		return transaction;
	}
	

	@Override
	public boolean deleteById(Integer transactionId) throws TransactionException {
		boolean deleted = false;
		if (!tranRepo.existsById(transactionId)) {
			throw new TransactionException("No Such transaction Found To Delete!");
		}

		tranRepo.deleteById(transactionId);
		deleted = true;

		return deleted;
	}

	@Override
	public Transaction getById(Integer transactionId) {
	
		return tranRepo.findById(transactionId).orElse(null);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return tranRepo.findAll();
	}


	

}

