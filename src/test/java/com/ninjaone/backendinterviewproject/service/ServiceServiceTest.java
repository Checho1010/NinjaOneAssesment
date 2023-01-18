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

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.dto.response.ServiceDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.impl.ServiceServiceImpl;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class ServiceServiceTest {

	public static final Service SERVICE = new Service();
	public static final List<ServiceAssignation> SERVICE_ASSIGNATION_LIST = new ArrayList<>();

	private static final Optional<Service> OPTIONAL_SERVICE = Optional.of(SERVICE);

	ServiceDTO SERVICE_REST = new ServiceDTO();
	List<ServiceAssignationDTO> SERVICE_ASSIGNATION_DTO_LIST = new ArrayList<>();

	@Mock
	ServiceRepository serviceRepository;

	@InjectMocks
	ServiceServiceImpl serviceServiceImpl;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		SERVICE.setId(ConstantsTest.SERVICE_ID);
		SERVICE.setDescription(ConstantsTest.SERVICE_DESCRIPTION);
		SERVICE.setCost(ConstantsTest.SERVICE_COST);
		SERVICE.setType(ConstantsTest.SERVICE_TYPE);
		SERVICE.setServiceAssignations(SERVICE_ASSIGNATION_LIST);

		SERVICE_REST.setId(ConstantsTest.SERVICE_ID);
		SERVICE_REST.setDescription(ConstantsTest.SERVICE_DESCRIPTION);
		SERVICE_REST.setCost(ConstantsTest.SERVICE_COST);
		SERVICE_REST.setType(ConstantsTest.SERVICE_TYPE);
		SERVICE_REST.setServiceAssignations(SERVICE_ASSIGNATION_DTO_LIST);
	}

	@Test
	public void getServiceByIdTestOK() throws RmmException {
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(Optional.of(SERVICE));
		serviceServiceImpl.getServiceById(ConstantsTest.SERVICE_ID);
	}

	@Test
	public void getServiceByIdTestError() throws RmmException {
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(Optional.empty());
		serviceServiceImpl.getServiceById(ConstantsTest.SERVICE_ID);
		fail();
	}

	@Test
	public void getServicesTest() throws RmmException {
		final Service service = new Service();
		Mockito.when(serviceRepository.findAll()).thenReturn(Arrays.asList(service));
		final List<ServiceDTO> response = serviceServiceImpl.getServices();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void createServiceTestOK() throws RmmException {
		Mockito.when(serviceRepository.save(Mockito.any(Service.class))).thenReturn(new Service());
		serviceServiceImpl.createService(SERVICE_REST);
	}

	@Test
	public void createServiceAlreadyExistTestError() throws RmmException {
		Mockito.when(serviceRepository.findByDescriptionAndType(ConstantsTest.SERVICE_DESCRIPTION,
				ConstantsTest.SERVICE_TYPE)).thenReturn(OPTIONAL_SERVICE);
		serviceServiceImpl.createService(SERVICE_REST);
		fail();
	}

	@Test
	public void createServiceWrongTypeTypeTestError() throws RmmException {
		SERVICE_REST.setType("NotAndroid");
		Mockito.when(serviceRepository.save(Mockito.any(Service.class))).thenReturn(new Service());
		serviceServiceImpl.createService(SERVICE_REST);
	}

	@Test
	public void createServiceInternalServerErrorTestError() throws RmmException {
		Mockito.doThrow(RmmException.class).when(serviceRepository).save(Mockito.any(Service.class));
		serviceServiceImpl.createService(SERVICE_REST);
		fail();
	}

	@Test
	public void deleteServiceTestOK() throws RmmException {
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(Optional.of(SERVICE));

		final String response = serviceServiceImpl.deleteService(ConstantsTest.SERVICE_ID);
		assertEquals(response, ConstantsTest.SERVICE_DELETED);
	}

	@Test
	public void deleteServiceNotFoundTestError() throws RmmException {
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(Optional.empty());

		final String response = serviceServiceImpl.deleteService(ConstantsTest.SERVICE_ID);
		assertEquals(response, ConstantsTest.SERVICE_DELETED);
		fail();
	}

	@Test
	public void deleteServiceInternalServerErrorTestError() throws RmmException {
		Mockito.when(serviceRepository.findById(ConstantsTest.SERVICE_ID)).thenReturn(Optional.of(SERVICE));

		Mockito.doThrow(RmmException.class).when(serviceRepository).deleteById(ConstantsTest.SERVICE_ID);

		final String response = serviceServiceImpl.deleteService(ConstantsTest.SERVICE_ID);
		assertEquals(response, ConstantsTest.SERVICE_DELETED);
		fail();
	}
}
