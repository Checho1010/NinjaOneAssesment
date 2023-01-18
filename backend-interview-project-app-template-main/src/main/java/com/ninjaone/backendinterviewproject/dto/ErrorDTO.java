package com.ninjaone.backendinterviewproject.dto;

import java.io.Serializable;

/**
 * @author CGomez DTO to manage errors
 *
 */
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = -9032516751365310076L;
	private String name;
	private String value;

	public ErrorDTO(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
