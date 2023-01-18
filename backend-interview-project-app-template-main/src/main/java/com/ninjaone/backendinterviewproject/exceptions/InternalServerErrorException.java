package com.ninjaone.backendinterviewproject.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author CGomez Exception class related to internal server errors
 *
 */
public class InternalServerErrorException extends RmmException {

	private static final long serialVersionUID = -7517070107014502894L;

	public InternalServerErrorException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

}
