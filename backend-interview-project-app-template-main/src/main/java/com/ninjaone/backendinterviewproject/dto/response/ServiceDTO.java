package com.ninjaone.backendinterviewproject.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author CGomez
 *
 *         This class is used to represent the entity Service in a json message
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDTO {
	@JsonProperty("id")
	private String id;
	@JsonProperty("description")
	private String description;
	@JsonProperty("type")
	private String type;
	@JsonProperty("cost")
	private BigDecimal cost;
	@JsonProperty("serviceAssignations")
	private List<ServiceAssignationDTO> serviceAssignations;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<ServiceAssignationDTO> getServiceAssignations() {
		return serviceAssignations;
	}

	public void setServiceAssignations(List<ServiceAssignationDTO> serviceAssignations) {
		this.serviceAssignations = serviceAssignations;
	}

}
