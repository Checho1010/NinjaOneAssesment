package com.ninjaone.backendinterviewproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.dto.response.CustomerDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.CustomerService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez this class enables the endpoints for Customers
 *
 */
@RestController
@CrossOrigin(origins = Constants.PATH_AND_PORT_ALLOWED_CLIENT)
@RequestMapping(Constants.ENDPOINT_PATH_RMM + Constants.ENDPOINT_PATH_VERSION + Constants.ENDPOINT_PATH_CUSTOMER)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/**
	 * Endpoint to find a customer by a given id
	 * 
	 * @param id of the Customer to be found
	 * @return response of {@link CustomerDTO}
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<CustomerDTO> getCustomerById(@PathVariable String id) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					customerService.getCustomerById(id));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	/**
	 * Endpoint to find all the customers
	 * 
	 * @return response of a list of {@link CustomerDTO}
	 * @throws RmmException
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<List<CustomerDTO>> getCustomers() throws RmmException {
		return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
				customerService.getCustomers());
	}

	/**
	 * Endpoint to delete a Customer
	 * 
	 * @param customerId of the Customer to be deleted
	 * @return a String response of the method
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> deleteCustomer(@RequestParam String customerId) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					customerService.deleteCustomer(customerId));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	/**
	 * Endpoint to create a Customer
	 * 
	 * @param customerDTO REST object that represents the Customer entity
	 * @return a String response of the method
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					customerService.createCustomer(customerDTO));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}
}
