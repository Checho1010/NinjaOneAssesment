package com.ninjaone.backendinterviewproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ninjaone.backendinterviewproject.dto.response.CustomerDTO;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.CustomerService;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class CustomerControllerTest {

	public static final CustomerDTO CUSTOMER_REST = new CustomerDTO();
	public static final List<ServiceAssignationDTO> SERVICE_ASSIGNATION_LIST = new ArrayList<>();
	public static final List<CustomerDTO> CUSTOMER_REST_LIST = new ArrayList<>();

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		CUSTOMER_REST.setId(ConstantsTest.CUSTOMER_ID);
		CUSTOMER_REST.setName(ConstantsTest.CUSTOMER_NAME);
		CUSTOMER_REST.setNumberOfDevices(ConstantsTest.CUSTOMER_NUMBER_OF_DEVICES);
		CUSTOMER_REST.setServiceAssignations(SERVICE_ASSIGNATION_LIST);

		Mockito.when(customerService.getCustomerById(ConstantsTest.CUSTOMER_ID)).thenReturn(CUSTOMER_REST);
		Mockito.when(customerService.createCustomer(CUSTOMER_REST)).thenReturn(ConstantsTest.CUSTOMER_ID);
	}

	@Test
	public void getCustomerByIdTest() throws RmmException {
		final RmmResponse<CustomerDTO> response = customerController.getCustomerById(ConstantsTest.CUSTOMER_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), CUSTOMER_REST);
	}

	@Test
	public void getCustomersTest() throws RmmException {
		final RmmResponse<List<CustomerDTO>> response = customerController.getCustomers();

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), CUSTOMER_REST_LIST);
	}

	@Test
	public void createCustomerTest() throws RmmException {
		RmmResponse<String> response = customerController.createCustomer(CUSTOMER_REST);
		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.CUSTOMER_ID);
	}

	@Test
	public void deleteCustomerTest() throws RmmException {
		Mockito.when(customerService.deleteCustomer(ConstantsTest.CUSTOMER_ID))
				.thenReturn(ConstantsTest.CUSTOMER_DELETED);

		final RmmResponse<String> response = customerController.deleteCustomer(ConstantsTest.CUSTOMER_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.CUSTOMER_DELETED);
	}
}
