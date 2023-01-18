package com.ninjaone.backendinterviewproject.service;

import java.util.List;

import com.ninjaone.backendinterviewproject.dto.response.ServiceDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;

/**
 * @author CGomez
 *
 *         This interface provides the methods to list, delete and create
 *         Services
 */
public interface ServiceService {

	/**
	 * 
	 * @param serviceId id of the Service to be found
	 * @return {@link ServiceDTO} Rest object that results from the search
	 * @throws RmmException
	 */
	ServiceDTO getServiceById(String serviceId) throws RmmException;

	/**
	 * 
	 * @return list of all the registered {@link ServiceDTO}
	 * @throws RmmException
	 */
	public List<ServiceDTO> getServices() throws RmmException;

	/**
	 * 
	 * @param serviceId id of the Service to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	public String deleteService(String serviceId) throws RmmException;

	/**
	 * 
	 * @param serviceResponse a REST object that represents the Service entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	String createService(ServiceDTO serviceResponse) throws RmmException;
}
