package com.ninjaone.backendinterviewproject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author CGomez
 *
 *         This class is used to represent the entity ServiceAssignation in a
 *         json message
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceAssignationDTO {

	@JsonProperty("id")
	private String id;
	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("serviceId")
	private String serviceId;
	@JsonProperty("deviceId")
	private String deviceId;
	@JsonProperty("customerId")
	private String customerId;
	/*
	 * @JsonProperty("customer") private CustomerDTO customer;
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*
	 * public CustomerDTO getCustomer() { return customer; } public void
	 * setCustomer(CustomerDTO customer) { this.customer = customer; }
	 */

}
