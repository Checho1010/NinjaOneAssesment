/**
 * 
 */
package com.ninjaone.backendinterviewproject.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.ServiceAssignationRepository;
import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.impl.CostCalculationServiceImpl;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class CostCalculationServiceTest {

	private static final Customer CUSTOMER = new Customer();
	private static final Service SERVICE = new Service();

	private static final Optional<Customer> OPTIONAL_CUSTOMER = Optional.of(CUSTOMER);
	private static final Optional<Service> OPTIONAL_SERVICE = Optional.of(SERVICE);

	@Mock
	CustomerRepository customerRepository;

	@Mock
	ServiceAssignationRepository serviceAssignationRepository;

	@Mock
	ServiceRepository serviceRepository;

	@InjectMocks
	CostCalculationServiceImpl costCalculationServiceImpl;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void calculateServiceAssignationCostByCustomerIdTestOK() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(OPTIONAL_CUSTOMER);
		final ServiceAssignation serviceAssignation = new ServiceAssignation();
		serviceAssignation.setQuantity(ConstantsTest.SERVICE_ASSIGNATION_REST_QUANTITY);
		serviceAssignation.setService(new Service(ConstantsTest.SERVICE_ID, ConstantsTest.SERVICE_DESCRIPTION,
				ConstantsTest.SERVICE_COST, ConstantsTest.SERVICE_TYPE));
		Mockito.when(serviceAssignationRepository.findAllByCustomerId(ConstantsTest.CUSTOMER_ID))
				.thenReturn(Optional.of(Arrays.asList(serviceAssignation)));
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(OPTIONAL_SERVICE);

		costCalculationServiceImpl.calculateServiceAssignationCostByCustomerId(ConstantsTest.CUSTOMER_ID);
	}

	@Test
	public void calculateServiceAssignationCostByCustomerIdServiceAssignationsNotFoundTestError() throws RmmException {
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(OPTIONAL_CUSTOMER);
		costCalculationServiceImpl.calculateServiceAssignationCostByCustomerId(ConstantsTest.CUSTOMER_ID);
		fail();
	}

}
