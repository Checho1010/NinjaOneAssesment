package com.ninjaone.backendinterviewproject.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.ninjaone.backendinterviewproject.dto.ErrorDTO;

/**
 * @author CGomez main exception class
 */

public class RmmException extends RuntimeException {
	private static final long serialVersionUID = 1992386757139528677L;

	private final String code;
	private final int responseCode;
	private final List<ErrorDTO> errorList = new ArrayList<>();

	public RmmException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}

	public RmmException(String code, int responseCode, String message, List<ErrorDTO> errorList) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
	}

	public String getCode() {
		return code;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public List<ErrorDTO> getErrorList() {
		return errorList;
	}

}
