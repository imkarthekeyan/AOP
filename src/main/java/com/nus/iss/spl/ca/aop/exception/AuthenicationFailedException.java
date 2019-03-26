package com.nus.iss.spl.ca.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthenicationFailedException extends RuntimeException {

	private static final long serialVersionUID = -1140498008244246539L;

	public AuthenicationFailedException(String exception) {
		super(exception);
	}
}
