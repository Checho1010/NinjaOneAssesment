package com.ninjaone.backendinterviewproject.service;

import java.util.List;

import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;

/**
 * @author CGomez
 *
 *         This interface provides the methods to list, delete and create
 *         ServiceAssignations
 */
public interface ServiceAssignationService {

	/**
	 * 
	 * @param serviceAssignationId id of the ServiceAssignation to be found
	 * @return {@link ServiceAssignationDTO} Rest object that results from the
	 *         search
	 * @throws RmmException
	 */
	ServiceAssignationDTO getServiceAssignationById(String serviceAssignationId) throws RmmException;

	/**
	 * 
	 * @return list of all the registered {@link ServiceAssignationDTO}
	 * @throws RmmException
	 */
	public List<ServiceAssignationDTO> getServiceAssignations() throws RmmException;

	/**
	 * 
	 * @param serviceAssignationId id of the ServiceAsignation to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	public String deleteServiceAssignation(String serviceAssignationId) throws RmmException;

	/**
	 * 
	 * @param serviceAssignationResponse a REST object that represents the
	 *                                   ServiceAssignation entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	String createServiceAssignation(ServiceAssignationDTO serviceAssignationResponse) throws RmmException;

	/**
	 * 
	 * @param customerId id of the Customer of whom the method will return his
	 *                   service assignations
	 * @returnlist of the registered {@link ServiceAssignationDTO} that belongs to
	 *             the given customer
	 * @throws RmmException
	 */
	public List<ServiceAssignationDTO> getServiceAssignationsByCustomerId(String customerId) throws RmmException;
}
