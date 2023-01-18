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
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.ServiceAssignationService;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

//import com.ninjaone.backendinterviewproject.controller.ServiceAssignationController;
/**
 * @author CGomez
 *
 */
public class ServiceAssignationControllerTest {

	public static final ServiceAssignationDTO SERVICE_ASSIGNATION_REST = new ServiceAssignationDTO();
	// public static final List<E>
	public static final List<ServiceAssignationDTO> SERVICE_ASSIGNATION_REST_LIST = new ArrayList<>();

	@Mock
	ServiceAssignationService serviceAssignationService;

	@InjectMocks
	ServiceAssignationController serviceAssignationController;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		SERVICE_ASSIGNATION_REST.setId(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
		SERVICE_ASSIGNATION_REST.setServiceId(ConstantsTest.SERVICE_ASSIGNATION_REST_SERVICE_ID);
		SERVICE_ASSIGNATION_REST.setDeviceId(ConstantsTest.SERVICE_ASSIGNATION_REST_DEVICE_ID);
		SERVICE_ASSIGNATION_REST.setCustomerId(ConstantsTest.SERVICE_ASSIGNATION_REST_CUSTOMER_ID);
		SERVICE_ASSIGNATION_REST.setQuantity(ConstantsTest.SERVICE_ASSIGNATION_REST_QUANTITY);

		Mockito.when(serviceAssignationService.getServiceAssignationById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID))
				.thenReturn(SERVICE_ASSIGNATION_REST);
		Mockito.when(serviceAssignationService.createServiceAssignation(SERVICE_ASSIGNATION_REST))
				.thenReturn(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
	}

	@Test
	public void getServiceAssignationByIdTest() throws RmmException {
		final RmmResponse<ServiceAssignationDTO> response = serviceAssignationController
				.getServiceAssignationById(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), SERVICE_ASSIGNATION_REST);
	}

	@Test
	public void getServiceAssignationsTest() throws RmmException {
		final RmmResponse<List<ServiceAssignationDTO>> response = serviceAssignationController.getServiceAssignations();

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), SERVICE_ASSIGNATION_REST_LIST);
	}

	@Test
	public void createDeviceTest() throws RmmException {
		RmmResponse<String> response = serviceAssignationController.createServiceAssignation(SERVICE_ASSIGNATION_REST);
		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.SERVICE_ASSIGNATION_REST_ID);
	}

	@Test
	public void deleteServiceAssignationTest() throws RmmException {
		Mockito.when(serviceAssignationService.deleteServiceAssignation(ConstantsTest.SERVICE_ASSIGNATION_REST_ID))
				.thenReturn(ConstantsTest.SERVICE_ASSIGNATION_DELETED);

		final RmmResponse<String> response = serviceAssignationController
				.deleteServiceAssignation(ConstantsTest.SERVICE_ASSIGNATION_REST_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.SERVICE_ASSIGNATION_DELETED);
	}
}
