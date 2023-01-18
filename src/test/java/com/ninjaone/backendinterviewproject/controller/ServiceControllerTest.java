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

import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.dto.response.ServiceDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class ServiceControllerTest {

	public static final ServiceDTO SERVICE_REST = new ServiceDTO();
	public static final List<ServiceAssignationDTO> SERVICE_ASSIGNATION_LIST = new ArrayList<>();
	public static final List<ServiceDTO> SERVICE_REST_LIST = new ArrayList<>();

	@Mock
	ServiceService serviceService;

	@InjectMocks
	ServiceController serviceController;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		SERVICE_REST.setId(ConstantsTest.SERVICE_ID);
		SERVICE_REST.setDescription(ConstantsTest.SERVICE_DESCRIPTION);
		SERVICE_REST.setCost(ConstantsTest.SERVICE_COST);
		SERVICE_REST.setType(ConstantsTest.SERVICE_TYPE);
		SERVICE_REST.setServiceAssignations(SERVICE_ASSIGNATION_LIST);

		Mockito.when(serviceService.getServiceById(ConstantsTest.SERVICE_ID)).thenReturn(SERVICE_REST);
		Mockito.when(serviceService.createService(SERVICE_REST)).thenReturn(ConstantsTest.SERVICE_ID);
	}

	@Test
	public void getServiceByIdTest() throws RmmException {
		final RmmResponse<ServiceDTO> response = serviceController.getServiceById(ConstantsTest.SERVICE_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), SERVICE_REST);
	}

	@Test
	public void getServicesTest() throws RmmException {
		final RmmResponse<List<ServiceDTO>> response = serviceController.getServices();

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), SERVICE_REST_LIST);
	}

	@Test
	public void createServiceTest() throws RmmException {
		RmmResponse<String> response = serviceController.createService(SERVICE_REST);
		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.SERVICE_ID);
	}

	@Test
	public void deleteServiceTest() throws RmmException {
		Mockito.when(serviceService.deleteService(ConstantsTest.SERVICE_ID)).thenReturn(ConstantsTest.SERVICE_DELETED);

		final RmmResponse<String> response = serviceController.deleteService(ConstantsTest.SERVICE_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.SERVICE_DELETED);
	}

}
