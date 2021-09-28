package com.dxc.iba.service;

import java.util.List;

import com.dxc.iba.entity.Customer;
import com.dxc.iba.exception.CustomerException;

public interface CustomerService {

	Customer add(Customer customer) throws CustomerException;

	Customer update(Customer customer) throws CustomerException;

	boolean deleteById(Integer customerId) throws CustomerException;

	Customer getById(Integer customerId);

	List<Customer> getAllCustomers();

}
