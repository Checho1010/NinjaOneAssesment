package com.ninjaone.backendinterviewproject.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author CGomez
 *
 *         This class is used to represent the entity Device in a json message
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDTO {

	@JsonProperty("id")
	private String id;

	@JsonProperty("systemName")
	private String systemName;

	@JsonProperty("type")
	private String type;

	@JsonProperty("serviceAssignations")
	private List<ServiceAssignationDTO> serviceAssignations;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ServiceAssignationDTO> getServiceAssignations() {
		return serviceAssignations;
	}

	public void setServiceAssignations(List<ServiceAssignationDTO> serviceAssignations) {
		this.serviceAssignations = serviceAssignations;
	}

}