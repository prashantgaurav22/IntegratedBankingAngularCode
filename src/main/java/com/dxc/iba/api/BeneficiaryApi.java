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

import com.dxc.iba.entity.Beneficiary;
import com.dxc.iba.exception.BeneficiaryException;
import com.dxc.iba.service.BeneficiaryService;



@RestController
@CrossOrigin

@RequestMapping("/beneficiary")

public class BeneficiaryApi {
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@GetMapping
	public ResponseEntity<List<Beneficiary>> getAllBeneficiary() {
		return new ResponseEntity<List<Beneficiary>>(beneficiaryService.getAllBeneficiary(), HttpStatus.OK);
	}
	
	@GetMapping("/{account_Number:[0-9]{1,10}}")
	public ResponseEntity<Beneficiary> getById(@PathVariable("account_Number") int account_Number) {
		ResponseEntity<Beneficiary> response = null;

		Beneficiary beneficiary = beneficiaryService.getById(account_Number);

		if (beneficiary == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(beneficiary, HttpStatus.OK);
		}

		return response;
	}
	
	@PostMapping
	public ResponseEntity<Beneficiary> add(@Valid @RequestBody Beneficiary beneficiary, BindingResult result)
			throws BeneficiaryException {
		ResponseEntity<Beneficiary> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new BeneficiaryException(errMsg.toString());
		} else {
			beneficiaryService.add(beneficiary);
			response = new ResponseEntity<>(beneficiary, HttpStatus.OK);
		}

		return response;
	}
	
	@PutMapping
	public ResponseEntity<Beneficiary> update(@Valid @RequestBody Beneficiary beneficiary, BindingResult result)
			throws BeneficiaryException {
		ResponseEntity<Beneficiary> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new BeneficiaryException(errMsg.toString());
		} else {
			beneficiaryService.update(beneficiary);
			response = new ResponseEntity<>(beneficiary, HttpStatus.OK);
		}

		return response;
	}

	@DeleteMapping("/{account_Number}")
	public ResponseEntity<Void> deleteById(@PathVariable("account_Number") int account_Number) throws BeneficiaryException {
		beneficiaryService.deleteById(account_Number);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

