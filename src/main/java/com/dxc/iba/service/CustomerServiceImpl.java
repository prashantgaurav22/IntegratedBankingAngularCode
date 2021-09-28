package com.dxc.iba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.iba.entity.Account;
import com.dxc.iba.entity.Customer;
import com.dxc.iba.exception.AccountException;
import com.dxc.iba.exception.CustomerException;
import com.dxc.iba.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	public static Integer custId = 100;
	
	AccountServiceImpl acc = new AccountServiceImpl();

	@Autowired
	private CustomerRepository custRepo;

	@Transactional
	@Override
	public Customer add(Customer customer) throws CustomerException {

		if (customer != null) {
			if (custRepo.existsById(customer.getAadharNumber())) {
				throw new CustomerException(
						"An customer with the AadharCard " + customer.getAadharNumber() + " already exists!");
			}
			int id = acc.accountId++;
			customer.setAccount(id);

			/*
			 * try { acc.addDetails(); } catch (AccountException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); }
			 */

			customer.setCustomerId(custId = custId + 1);
			custRepo.save(customer);
		}
		return customer;
	}

	@Transactional
	@Override
	public Customer update(Customer customer) throws CustomerException {
		if (customer != null) {
			if (!custRepo.existsById(customer.getCustomerId())) {
				throw new CustomerException("No Customer Found To Update!");
			}

			custRepo.save(customer);
		}
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public boolean deleteById(Integer customerId) throws CustomerException {
		boolean deleted = false;
		if (!custRepo.existsById(customerId)) {
			throw new CustomerException("No Such Customer Found To Delete!");
		}

		custRepo.deleteById(customerId);
		deleted = true;
		return deleted;
	}

	@Override
	public Customer getById(Integer customerId) {
		return custRepo.findById(customerId).orElse(null);
	}
}
