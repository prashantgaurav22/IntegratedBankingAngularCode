package com.dxc.iba.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dxc.iba.exception.TransactionException;


public class TransactionExceptionAdvice {

	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<String> handleItemException(TransactionException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
