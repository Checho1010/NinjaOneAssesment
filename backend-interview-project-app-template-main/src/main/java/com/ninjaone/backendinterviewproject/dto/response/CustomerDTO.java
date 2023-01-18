package com.ninjaone.backendinterviewproject.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author CGomez
 *
 *         This class is used to represent the entity Customer in a json message
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("numberOfDevices")
	private int numberOfDevices;
	@JsonProperty("serviceAssignations")
	private List<ServiceAssignationDTO> serviceAssignations;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfDevices() {
		return numberOfDevices;
	}

	public void setNumberOfDevices(int numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
	}

	public List<ServiceAssignationDTO> getServiceAssignations() {
		return serviceAssignations;
	}

	public void setServiceAssignations(List<ServiceAssignationDTO> serviceAssignations) {
		this.serviceAssignations = serviceAssignations;
	}
}
