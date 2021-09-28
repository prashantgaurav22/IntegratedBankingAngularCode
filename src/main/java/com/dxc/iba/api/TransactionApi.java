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

import com.dxc.iba.entity.Transaction;
import com.dxc.iba.exception.TransactionException;
import com.dxc.iba.service.TransactionService;



@RestController
@CrossOrigin

@RequestMapping("/transactions")
public class TransactionApi {

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		return new ResponseEntity<List<Transaction>>(transactionService.getAllTransactions(), HttpStatus.OK);
	}

	@GetMapping("/{transactionId:[0-9]{1,10}}")
	public ResponseEntity<Transaction> getById(@PathVariable("transactionId") int transactionId) {
		ResponseEntity<Transaction> response = null;

		Transaction transaction = transactionService.getById(transactionId);

		if (transaction == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(transaction, HttpStatus.OK);
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<Transaction> add(@Valid @RequestBody Transaction transaction, BindingResult result)
			throws TransactionException {
		ResponseEntity<Transaction> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new TransactionException(errMsg.toString());
		} else {
			transactionService.add(transaction);
			response = new ResponseEntity<>(transaction, HttpStatus.OK);
		}

		return response;
	}

	@PutMapping
	public ResponseEntity<Transaction> update(@Valid @RequestBody Transaction transaction, BindingResult result)
			throws TransactionException {
		ResponseEntity<Transaction> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new TransactionException(errMsg.toString());
		} else {
			transactionService.update(transaction);
			response = new ResponseEntity<>(transaction, HttpStatus.OK);
		}

		return response;
	}

	@DeleteMapping("/{transactionId}")
	public ResponseEntity<Void> deleteById(@PathVariable("transactionId") int transactionId) throws TransactionException {
		transactionService.deleteById(transactionId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
