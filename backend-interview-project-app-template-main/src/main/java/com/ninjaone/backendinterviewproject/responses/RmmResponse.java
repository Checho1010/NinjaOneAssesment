package com.ninjaone.backendinterviewproject.responses;

import java.io.Serializable;

/**
 * @author CGomez This class represent the Response given to the client
 *
 */
public class RmmResponse<T> implements Serializable {

	private static final long serialVersionUID = -7803860144548198034L;

	private String status;
	private String code;
	private String message;
	private T data;

	public RmmResponse(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public RmmResponse(String status, String code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}