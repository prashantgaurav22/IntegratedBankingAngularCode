package com.dxc.iba.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.iba.entity.Customer;
import com.dxc.iba.exception.CustomerException;
import com.dxc.iba.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/registration")
public class CustomerApi {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/{customerId:[0-9]{1,5}}")
	public ResponseEntity<Customer> getById(@PathVariable("customerId") int customerId) {
		ResponseEntity<Customer> response = null;

		Customer customer = customerService.getById(customerId);

		if (customer == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(customer, HttpStatus.OK);
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<Customer> add(@Valid @RequestBody Customer customer, BindingResult result)
			throws CustomerException {
		ResponseEntity<Customer> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new CustomerException(errMsg.toString());
		} else {
			customerService.add(customer);
			response = new ResponseEntity<>(customer, HttpStatus.OK);
		}

		return response;
	}

	@PutMapping
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer, BindingResult result)
			throws CustomerException {
		ResponseEntity<Customer> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new CustomerException(errMsg.toString());
		} else {
			customerService.update(customer);
			response = new ResponseEntity<>(customer, HttpStatus.OK);
		}
		return response;
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteById(@PathVariable("customerId") int customerId) throws CustomerException {
		customerService.deleteById(customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
