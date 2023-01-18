package com.ninjaone.backendinterviewproject.model;

import java.math.BigDecimal;
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
 *         This class represents the SERVICE table
 */
@Entity
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String description;
	private String type;
	private BigDecimal cost;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
	private List<ServiceAssignation> serviceAssignations;

	public Service() {
	}

	public Service(String id, String description, BigDecimal cost, String type) {
		this.id = id;
		this.description = description;
		this.cost = cost;
		this.type = type;
	}

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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
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
