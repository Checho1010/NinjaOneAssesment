package com.ninjaone.backendinterviewproject.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.ServiceAssignationRepository;
import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationCostDTO;
import com.ninjaone.backendinterviewproject.exceptions.EntityNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.CostCalculationService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez
 *
 */
@Service()
public class CostCalculationServiceImpl implements CostCalculationService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ServiceAssignationRepository serviceAssignationRepository;

	@Autowired
	ServiceRepository serviceRepository;

	/**
	 * 
	 * @param customerId id of the customer of whom the method will calculate the
	 *                   costs of the services of his devices
	 * @return ServiceAssignationCostDTO a REST object that provides info about the
	 *         costs
	 * @throws RmmException
	 */
	@Override
	@Cacheable(Constants.COST_CALCULATION_CACHE)
	public ServiceAssignationCostDTO calculateServiceAssignationCostByCustomerId(String customerId)
			throws RmmException {

		final Customer customerEntity = customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.CUSTOMER_ID_NOT_FOUND_IN_COST_CALCULATION,
						Constants.CUSTOMER_ID_NOT_FOUND_IN_COST_CALCULATION));

		final List<ServiceAssignation> serviceAssignationList = (List<ServiceAssignation>) serviceAssignationRepository
				.findAllByCustomerId(customerId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID,
						Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID));
		if (serviceAssignationList.size() == 0) {
			throw new EntityNotFoundException(Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID,
					Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID);
		}

		ServiceAssignationCostDTO response = new ServiceAssignationCostDTO();

		response.setDevicesQuantity(customerEntity.getNumberOfDevices());
		response.setDevicesCost(BigDecimal.valueOf(customerEntity.getNumberOfDevices() * Constants.COST_PER_DEVICE));
		response.setServicesQuantity(0);
		response.setServicesCost(BigDecimal.ZERO);

		serviceAssignationList.stream().forEach(serviceAssignation -> {
			response.setServicesQuantity(response.getServicesQuantity() + serviceAssignation.getQuantity());
			final com.ninjaone.backendinterviewproject.model.Service service = serviceRepository
					.findById(serviceAssignation.getService().getId())
					.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ID_NOT_FOUND_IN_COST_CALCULATION,
							Constants.SERVICE_ID_NOT_FOUND_IN_COST_CALCULATION));
			response.setServicesCost(response.getServicesCost()
					.add(BigDecimal.valueOf(serviceAssignation.getQuantity()).multiply(service.getCost())));
		});

		response.setTotalCost(response.getDevicesCost().add(response.getServicesCost()));
		return response;
	}

}
