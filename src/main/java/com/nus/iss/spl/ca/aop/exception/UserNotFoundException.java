package com.nus.iss.spl.ca.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8426943131488443567L;

	public UserNotFoundException(String exception) {
		super(exception);
	}
}