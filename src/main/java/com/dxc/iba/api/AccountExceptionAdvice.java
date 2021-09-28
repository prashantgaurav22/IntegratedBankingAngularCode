package com.dxc.iba.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dxc.iba.exception.AccountException;


@RestControllerAdvice
public class AccountExceptionAdvice {

	@ExceptionHandler(AccountException.class)
	public ResponseEntity<String> handleItemException(AccountException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
