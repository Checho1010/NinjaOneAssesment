package com.ninjaone.backendinterviewproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author CGomez
 *
 *         This class represents the DEVICE table
 */
@Entity

public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String systemName;
	private String type;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "device")
	private List<ServiceAssignation> serviceAssignations;

	public Device() {
	}

	public Device(String id, String systemName, String type) {
		this.id = id;
		this.systemName = systemName;
		this.type = type;
	}

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

	public List<ServiceAssignation> getServiceAssignations() {
		return serviceAssignations;
	}

	public void setServiceAssignations(List<ServiceAssignation> serviceAssignations) {
		this.serviceAssignations = serviceAssignations;
	}

}