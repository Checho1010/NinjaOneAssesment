package com.ninjaone.backendinterviewproject.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author CGomez Exception class related to entity searching issues
 *
 */
public class EntityNotFoundException extends RmmException {

	private static final long serialVersionUID = -2164657805069772445L;

	public EntityNotFoundException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}

}
