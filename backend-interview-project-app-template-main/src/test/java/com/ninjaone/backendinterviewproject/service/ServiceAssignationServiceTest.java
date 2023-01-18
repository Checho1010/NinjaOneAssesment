package com.ninjaone.backendinterviewproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServiceAssignationRepository;
import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.impl.ServiceAssignationServiceImpl;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class ServiceAssignationServiceTest {

	ServiceAssignationDTO SERVICE_ASSIGNATION_REST = new ServiceAssignationDTO();

	private static final ServiceAssignation SERVICE_ASSIGNATION = new ServiceAssignation();
	private static final Service SERVICE = new Service();
	private static final Device DEVICE = new Device();
	private static final Customer CUSTOMER = new Customer();

	private static final Optional<Service> OPTIONAL_SERVICE = Optional.of(SERVICE);
	private static final Optional<Device> OPTIONAL_DEVICE = Optional.of(DEVICE);
	private static final Optional<Customer> OPTIONAL_CUSTOMER = Optional.of(CUSTOMER);

	private static final Optional<ServiceAssignation> OPTIONAL_SERVICE_ASSIGNATION = Optional.of(SERVICE_ASSIGNATION);

	@Mock
	ServiceRepository serviceRepository;

	@Mock
	DeviceRepository deviceRepository;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	ServiceAssignationRepository serviceAssignationRepository;

	@InjectMocks
	ServiceAssignationServiceImpl serviceAssignationServiceImpl;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		SERVICE.setType(ConstantsTest.SERVICE_TYPE);
		DEVICE.setType(ConstantsTest.SERVICE_TYPE);

		SERVICE_ASSIGNATION_REST.setId(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
		SERVICE_ASSIGNATION_REST.setServiceId(ConstantsTest.SERVICE_ASSIGNATION_REST_SERVICE_ID);
		SERVICE_ASSIGNATION_REST.setDeviceId(ConstantsTest.SERVICE_ASSIGNATION_REST_DEVICE_ID);
		SERVICE_ASSIGNATION_REST.setCustomerId(ConstantsTest.SERVICE_ASSIGNATION_REST_CUSTOMER_ID);
		SERVICE_ASSIGNATION_REST.setQuantity(ConstantsTest.SERVICE_ASSIGNATION_REST_QUANTITY);
	}

	@Test
	public void CreateServiceAssignationTestOK() throws RmmException {
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(OPTIONAL_SERVICE);
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(OPTIONAL_DEVICE);
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(OPTIONAL_CUSTOMER);

		Mockito.when(serviceAssignationRepository.save(Mockito.any(ServiceAssignation.class)))
				.thenReturn(new ServiceAssignation());
		serviceAssignationServiceImpl.createServiceAssignation(SERVICE_ASSIGNATION_REST);
	}

	@Test
	public void CreateServiceAssignationAlreadyExistTestError() throws RmmException {
		Mockito.when(serviceAssignationRepository.findByServiceIdAndDeviceIdAndCustomerId(ConstantsTest.SERVICE_ID,
				ConstantsTest.DEVICE_ID, ConstantsTest.CUSTOMER_ID)).thenReturn(OPTIONAL_SERVICE_ASSIGNATION);
		serviceAssignationServiceImpl.createServiceAssignation(SERVICE_ASSIGNATION_REST);
		fail();
	}

	@Test
	public void CreateServiceAssignationInternalServerErrorTestError() throws RmmException {
		Mockito.doThrow(RmmException.class).when(serviceAssignationRepository)
				.save(Mockito.any(ServiceAssignation.class));
		serviceAssignationServiceImpl.createServiceAssignation(SERVICE_ASSIGNATION_REST);
		fail();
	}

	@Test
	public void CreateServiceAssignationTypesNotMatchTestError() throws RmmException {
		DEVICE.setType(ConstantsTest.DEVICE_TYPE);
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(OPTIONAL_SERVICE);
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(OPTIONAL_DEVICE);
		Mockito.when(customerRepository.findById(ConstantsTest.CUSTOMER_ID)).thenReturn(OPTIONAL_CUSTOMER);

		Mockito.when(serviceAssignationRepository.save(Mockito.any(ServiceAssignation.class)))
				.thenReturn(new ServiceAssignation());
		serviceAssignationServiceImpl.createServiceAssignation(SERVICE_ASSIGNATION_REST);
		fail();
	}
 
	@Test
	public void getServiceAssignationTestOK() throws RmmException {
		Mockito.when(serviceAssignationRepository.findById(ConstantsTest.SERVICE_ASSIGNATION_REST_DEVICE_ID))
				.thenReturn(Optional.of(SERVICE_ASSIGNATION));
		serviceAssignationServiceImpl.getServiceAssignationById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
	}

	@Test
	public void getServiceAssignationsTest() throws RmmException {
		final ServiceAssignation serviceAssignation = new ServiceAssignation();
		Mockito.when(serviceAssignationRepository.findAll()).thenReturn(Arrays.asList(serviceAssignation));
		final List<ServiceAssignationDTO> response = serviceAssignationServiceImpl.getServiceAssignations();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void deleteServiceAssignationTestOK() throws RmmException {
		Mockito.when(serviceAssignationRepository.findById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID))
				.thenReturn(Optional.of(SERVICE_ASSIGNATION));

		final String response = serviceAssignationServiceImpl
				.deleteServiceAssignation(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
		assertEquals(response, ConstantsTest.SERVICE_ASSIGNATION_DELETED);
	}

	@Test
	public void deleteServiceAssignationNotFoundTestError() throws RmmException {
		Mockito.when(serviceAssignationRepository.findById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID))
				.thenReturn(Optional.empty());

		final String response = serviceAssignationServiceImpl
				.deleteServiceAssignation(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
		assertEquals(response, ConstantsTest.SERVICE_ASSIGNATION_DELETED);
		fail();
	}

	@Test
	public void deleteServiceAssignationInternalServerErrorTestError() throws RmmException {
		Mockito.when(serviceAssignationRepository.findById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID))
				.thenReturn(Optional.of(SERVICE_ASSIGNATION));
		Mockito.doThrow(RmmException.class).when(serviceAssignationRepository)
				.deleteById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);

		final String response = serviceAssignationServiceImpl
				.deleteServiceAssignation(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
		assertEquals(response, ConstantsTest.SERVICE_ASSIGNATION_DELETED);
		fail();
	}

}
