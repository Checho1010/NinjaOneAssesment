package com.ninjaone.backendinterviewproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.dto.response.CustomerDTO;
import com.ninjaone.backendinterviewproject.exceptions.EntityNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.InternalServerErrorException;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.service.CustomerService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez
 *
 */
@Service()
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * 
	 * @param customerId id of the Customer to be found
	 * @return {@link CustomerDTO} Rest object that results from the search
	 * @throws RmmException
	 */
	@Override
	public CustomerDTO getCustomerById(String customerId) throws RmmException {
		return modelMapper.map(getCustomerEntity(customerId), CustomerDTO.class);
	}

	/**
	 * 
	 * @return list of all the registered {@link CustomerDTO}
	 * @throws RmmException
	 */
	@Override
	public List<CustomerDTO> getCustomers() throws RmmException {
		final List<Customer> customersEntity = (List<Customer>) customerRepository.findAll();
		return customersEntity.stream().map(service -> modelMapper.map(service, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Local method to invoke the {@link CustomerRepository} object
	 * 
	 * @param customerId id of the Service to be found
	 * @return {@link Customer}
	 * @throws RmmException
	 */
	private Customer getCustomerEntity(String customerId) throws RmmException {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.CUSTOMER_ID_NOT_FOUND_IN_GET_ENTITY,
						Constants.CUSTOMER_ID_NOT_FOUND_IN_GET_ENTITY));
	}

	/**
	 * 
	 * @param customerId id of the Customer to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	public String deleteCustomer(String customerId) throws RmmException {
		customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.CUSTOMER_ID_NOT_FOUND_IN_DELETE_ENTITY,
						Constants.CUSTOMER_ID_NOT_FOUND_IN_DELETE_ENTITY));
		try {
			customerRepository.deleteById(customerId);
		} catch (RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " customerId: " + customerId, e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return Constants.CUSTOMER_DELETED;
	}

	/**
	 * 
	 * @param customerResponse a REST object that represents the Customer entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	public String createCustomer(CustomerDTO customerResponse) throws RmmException {
		if (customerRepository.findByName(customerResponse.getName()).isPresent()) {
			throw new EntityNotFoundException(Constants.CUSTOMER_ALREADY_EXIST, Constants.CUSTOMER_ALREADY_EXIST);
		}
		final Customer customer = new Customer();
		customer.setName(customerResponse.getName());
		customer.setNumberOfDevices(customerResponse.getNumberOfDevices());
		try {
			customerRepository.save(customer);
		} catch (Exception e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " customer name: " + customerResponse.getName(), e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return customer.getId();
	}

}
