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

import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class DeviceControllerTest {

	public static final DeviceDTO DEVICE_REST = new DeviceDTO();
	public static final List<ServiceAssignationDTO> SERVICE_ASSIGNATION_LIST = new ArrayList<>();
	public static final List<DeviceDTO> DEVICE_REST_LIST = new ArrayList<>();

	@Mock
	DeviceService deviceService;

	@InjectMocks
	DeviceController deviceController;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		DEVICE_REST.setId(ConstantsTest.DEVICE_ID);
		DEVICE_REST.setSystemName(ConstantsTest.DEVICE_SYSTEM_NAME);
		DEVICE_REST.setType(ConstantsTest.DEVICE_TYPE);
		DEVICE_REST.setServiceAssignations(SERVICE_ASSIGNATION_LIST);

		Mockito.when(deviceService.getDeviceById(ConstantsTest.DEVICE_ID)).thenReturn(DEVICE_REST);
		Mockito.when(deviceService.createDevice(DEVICE_REST)).thenReturn(ConstantsTest.DEVICE_ID);

	}

	@Test
	public void getDeviceByIdTest() throws RmmException {
		final RmmResponse<DeviceDTO> response = deviceController.getDeviceById(ConstantsTest.DEVICE_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), DEVICE_REST);
	}

	@Test
	public void getDevicesTest() throws RmmException {
		final RmmResponse<List<DeviceDTO>> response = deviceController.getDevices();

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), DEVICE_REST_LIST);

	}

	@Test
	public void createDeviceTest() throws RmmException {
		RmmResponse<String> response = deviceController.createDevice(DEVICE_REST);
		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.DEVICE_ID);
	}

	@Test
	public void deleteDeviceTest() throws RmmException {
		Mockito.when(deviceService.deleteDevice(ConstantsTest.DEVICE_ID)).thenReturn(ConstantsTest.DEVICE_DELETED);

		final RmmResponse<String> response = deviceController.deleteDevice(ConstantsTest.DEVICE_ID);

		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ConstantsTest.DEVICE_DELETED);
	}

}
