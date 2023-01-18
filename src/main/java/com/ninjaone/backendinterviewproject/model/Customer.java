package com.ninjaone.backendinterviewproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author CGomez
 * 
 *         This class represents the CUSTOMER table
 *
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	@Column(name = "NUMBER_OF_DEVICES")
	private int numberOfDevices;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private List<ServiceAssignation> serviceAssignations;

	public Customer() {
	}

	public Customer(String id, String name, int numberOfDevices) {
		this.id = id;
		this.name = name;
		this.numberOfDevices = numberOfDevices;
	}

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

	public List<ServiceAssignation> getServiceAssignations() {
		return serviceAssignations;
	}

	public void setServiceAssignations(List<ServiceAssignation> serviceAssignations) {
		this.serviceAssignations = serviceAssignations;
	}

}