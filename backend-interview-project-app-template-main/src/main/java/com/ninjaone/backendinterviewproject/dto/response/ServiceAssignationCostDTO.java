package com.ninjaone.backendinterviewproject.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author CGomez
 *
 *         This class is used to represent the response of the cost calculation
 *         method in a json trace
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceAssignationCostDTO {

	@JsonProperty("devicesQuantity")
	private int devicesQuantity;

	@JsonProperty("devicesCost")
	private BigDecimal devicesCost;

	@JsonProperty("servicesQuantity")
	private int servicesQuantity;

	@JsonProperty("servicesCost")
	private BigDecimal servicesCost;

	@JsonProperty("totalCost")
	private BigDecimal totalCost;

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public int getServicesQuantity() {
		return servicesQuantity;
	}

	public void setServicesQuantity(int servicesQuantity) {
		this.servicesQuantity = servicesQuantity;
	}

	public int getDevicesQuantity() {
		return devicesQuantity;
	}

	public void setDevicesQuantity(int devicesQuantity) {
		this.devicesQuantity = devicesQuantity;
	}

	public BigDecimal getDevicesCost() {
		return devicesCost;
	}

	public void setDevicesCost(BigDecimal devicesCost) {
		this.devicesCost = devicesCost;
	}

	public BigDecimal getServicesCost() {
		return servicesCost;
	}

	public void setServicesCost(BigDecimal servicesCost) {
		this.servicesCost = servicesCost;
	}

}