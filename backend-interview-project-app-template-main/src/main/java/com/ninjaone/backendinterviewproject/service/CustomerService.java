package com.ninjaone.backendinterviewproject.service;

import java.util.List;

import com.ninjaone.backendinterviewproject.dto.response.CustomerDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;

/**
 * @author CGomez
 *
 *         This interface provides the methods to list, delete and create
 *         Customers
 */
public interface CustomerService {

	/**
	 * 
	 * @param customerId id of the Customer to be found
	 * @return {@link CustomerDTO} Rest object that results from the search
	 * @throws RmmException
	 */
	CustomerDTO getCustomerById(String customerId) throws RmmException;

	/**
	 * 
	 * @return list of all the registered {@link CustomerDTO}
	 * @throws RmmException
	 */
	public List<CustomerDTO> getCustomers() throws RmmException;

	/**
	 * 
	 * @param customerId id of the Customer to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	public String deleteCustomer(String customerId) throws RmmException;

	/**
	 * 
	 * @param customerResponse a REST object that represents the Customer entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	String createCustomer(CustomerDTO customerResponse) throws RmmException;
}
