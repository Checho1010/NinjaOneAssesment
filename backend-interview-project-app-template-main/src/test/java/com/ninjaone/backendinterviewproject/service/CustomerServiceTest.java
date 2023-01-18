package com.ninjaone.backendinterviewproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.dto.response.CustomerDTO;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.impl.CustomerServiceImpl;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class CustomerServiceTest {

	public static final Customer CUSTOMER = new Customer();
	public static final List<ServiceAssignation> SERVICE_ASSIGNATION_LIST = new ArrayList<>();

	private static final Optional<Customer> OPTIONAL_CUSTOMER = Optional.of(CUSTOMER);

	CustomerDTO CUSTOMER_REST = new CustomerDTO();
	List<ServiceAssignationDTO> SERVICE_ASSIGNATION_DTO_LIST = new ArrayList<>();

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		CUSTOMER.setId(ConstantsTest.CUSTOMER_ID);
		CUSTOMER.setName(ConstantsTest.CUSTOMER_NAME);
		CUSTOMER.setNumberOfDevices(ConstantsTest.CUSTOMER_NUMBER_OF_DEVICES);
		CUSTOMER.setServiceAssignations(SERVICE_ASSIGNATION_LIST);

		CUSTOMER_REST.setId(ConstantsTest.CUSTOMER_ID);
		CUSTOMER_REST.setName(ConstantsTest.CUSTOMER_NAME);
		CUSTOMER_REST.setNumberOfDevices(ConstantsTest.CUSTOMER_NUMBER_OF_DEVICES);
		CUSTOMER_REST.setServiceAssignations(SERVICE_ASSIGNATION_DTO_LIST);

	}

	@Test
	public void getCustomerByIdTestOK() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
		customerServiceImpl.getCustomerById(ConstantsTest.CUSTOMER_ID);
	}

	@Test
	public void getCustomerByIdTestError() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(Optional.empty());
		customerServiceImpl.getCustomerById(ConstantsTest.CUSTOMER_ID);
		fail();
	}

	@Test
	public void getCustomersTest() throws RmmException {
		final Customer customer = new Customer();
		Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
		final List<CustomerDTO> response = customerServiceImpl.getCustomers();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void createCustomerTestOK() throws RmmException {
		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(new Customer());
		customerServiceImpl.createCustomer(CUSTOMER_REST);
	}

	@Test
	public void createCustomerAlreadyExistTestError() throws RmmException {
		Mockito.when(customerRepository.findByName(ConstantsTest.CUSTOMER_NAME)).thenReturn(OPTIONAL_CUSTOMER);
		customerServiceImpl.createCustomer(CUSTOMER_REST);
		fail();
	}

	@Test
	public void createCustomerInternalServerErrorTestError() throws RmmException {
		Mockito.doThrow(RmmException.class).when(customerRepository).save(Mockito.any(Customer.class));
		customerServiceImpl.createCustomer(CUSTOMER_REST);
		fail();
	}

	@Test
	public void deleteCustomerTestOK() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
		final String response = customerServiceImpl.deleteCustomer(ConstantsTest.CUSTOMER_ID);
		assertEquals(response, ConstantsTest.CUSTOMER_DELETED);
	}

	@Test
	public void deleteCustomerNotFoundTestError() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(Optional.empty());

		final String response = customerServiceImpl.deleteCustomer(ConstantsTest.CUSTOMER_ID);
		assertEquals(response, ConstantsTest.CUSTOMER_DELETED);
		fail();
	}

	@Test
	public void deleteCustomerInternalServerErrorTestError() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));

		Mockito.doThrow(RmmException.class).when(customerRepository).deleteById(ConstantsTest.CUSTOMER_ID);

		final String response = customerServiceImpl.deleteCustomer(ConstantsTest.CUSTOMER_ID);
		assertEquals(response, ConstantsTest.CUSTOMER_DELETED);
		fail();
	}

}
