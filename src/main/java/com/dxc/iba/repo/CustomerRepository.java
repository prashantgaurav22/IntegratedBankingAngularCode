package com.dxc.iba.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.iba.entity.Customer;
import com.dxc.iba.exception.CustomerException;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	
	

}
