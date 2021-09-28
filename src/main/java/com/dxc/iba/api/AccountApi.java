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

import com.dxc.iba.entity.Account;
import com.dxc.iba.exception.AccountException;
import com.dxc.iba.service.AccountService;


@RestController
@CrossOrigin

@RequestMapping("/accounts")
public class AccountApi {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
	}

	@GetMapping("/{accountNumber:[0-9]{1,5}}")
	public ResponseEntity<Account> getById(@PathVariable("accountNumber") int accountNumber) {
		ResponseEntity<Account> response = null;

		Account account = accountService.getById(accountNumber);

		if (account == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(account, HttpStatus.OK);
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<Account> add(@Valid @RequestBody Account account, BindingResult result)
			throws AccountException {
		ResponseEntity<Account> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new AccountException(errMsg.toString());
		} else {
			accountService.add(account);
			response = new ResponseEntity<>(account, HttpStatus.OK);
		}

		return response;
	}

	@PutMapping
	public ResponseEntity<Account> update(@Valid @RequestBody Account account, BindingResult result)
			throws AccountException {
		ResponseEntity<Account> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new AccountException(errMsg.toString());
		} else {
			accountService.update(account);
			response = new ResponseEntity<>(account, HttpStatus.OK);
		}

		return response;
	}

	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<Void> deleteById(@PathVariable("accountNumber") int accountNumber) throws AccountException {
		accountService.deleteById(accountNumber);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

