package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationCostDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;

/**
 * @author CGomez
 *
 *         This interface provides the method that calculates the cost of the
 *         services assigned to the devices of a customer
 * 
 */
public interface CostCalculationService {

	/**
	 * 
	 * @param customerId id of the customer of whom the method will calculate the
	 *                   costs of the services of his devices
	 * @return ServiceAssignationCostDTO a REST object that provides info about the
	 *         costs
	 * @throws RmmException
	 */
	ServiceAssignationCostDTO calculateServiceAssignationCostByCustomerId(String customerId) throws RmmException;
}
