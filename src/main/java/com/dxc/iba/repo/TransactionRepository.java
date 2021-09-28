package com.dxc.iba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.iba.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>  {

	
}
