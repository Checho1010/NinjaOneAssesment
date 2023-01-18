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

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.impl.DeviceServiceImpl;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class DeviceServiceTest {

	public static final Device DEVICE = new Device();
	public static final List<ServiceAssignation> SERVICE_ASSIGNATION_LIST = new ArrayList<>();

	private static final Optional<Device> OPTIONAL_DEVICE = Optional.of(DEVICE);

	DeviceDTO DEVICE_REST = new DeviceDTO();
	List<ServiceAssignationDTO> SERVICE_ASSIGNATION_DTO_LIST = new ArrayList<>();

	@Mock
	DeviceRepository deviceRepository;

	@InjectMocks
	DeviceServiceImpl deviceServiceImpl;

	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);

		DEVICE.setId(ConstantsTest.DEVICE_ID);
		DEVICE.setSystemName(ConstantsTest.DEVICE_SYSTEM_NAME);
		DEVICE.setType(ConstantsTest.DEVICE_TYPE);
		DEVICE.setServiceAssignations(SERVICE_ASSIGNATION_LIST);

		DEVICE_REST.setId(ConstantsTest.DEVICE_ID);
		DEVICE_REST.setSystemName(ConstantsTest.DEVICE_SYSTEM_NAME);
		DEVICE_REST.setType(ConstantsTest.DEVICE_TYPE);
		DEVICE_REST.setServiceAssignations(SERVICE_ASSIGNATION_DTO_LIST);

	}

	@Test
	public void getDeviceByIdTestOK() throws RmmException {
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(Optional.of(DEVICE));
		deviceServiceImpl.getDeviceById(ConstantsTest.DEVICE_ID);
	}

	@Test
	public void getDeviceByIdTestError() throws RmmException {
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(Optional.empty());
		deviceServiceImpl.getDeviceById(ConstantsTest.DEVICE_ID);
		fail();
	}

	@Test
	public void getDevicesTest() throws RmmException {
		final Device device = new Device();
		Mockito.when(deviceRepository.findAll()).thenReturn(Arrays.asList(device));
		final List<DeviceDTO> response = deviceServiceImpl.getDevices();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void createDeviceTestOK() throws RmmException {
		Mockito.when(deviceRepository.save(Mockito.any(Device.class))).thenReturn(new Device());
		deviceServiceImpl.createDevice(DEVICE_REST);
	}

	@Test
	public void createDeviceAlreadyExistTestError() throws RmmException {
		Mockito.when(
				deviceRepository.findBySystemNameAndType(ConstantsTest.DEVICE_SYSTEM_NAME, ConstantsTest.DEVICE_TYPE))
				.thenReturn(OPTIONAL_DEVICE);

		deviceServiceImpl.createDevice(DEVICE_REST);
		fail();
	}

	@Test
	public void createDeviceWrongTypeTestError() throws RmmException {
		DEVICE_REST.setType("Linux123");
		Mockito.when(deviceRepository.save(Mockito.any(Device.class))).thenReturn(new Device());
		deviceServiceImpl.createDevice(DEVICE_REST);
	}

	@Test
	public void createDeviceInternalServerErrrorTestError() throws RmmException {
		Mockito.doThrow(RmmException.class).when(deviceRepository).save(Mockito.any(Device.class));
		deviceServiceImpl.createDevice(DEVICE_REST);
		fail();
	}

	@Test
	public void deleteDeviceTestOK() throws RmmException {
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(Optional.of(DEVICE));

		final String response = deviceServiceImpl.deleteDevice(ConstantsTest.DEVICE_ID);
		assertEquals(response, ConstantsTest.DEVICE_DELETED);
	}

	@Test
	public void deleteDeviceNotFoundTestError() throws RmmException {
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(Optional.empty());

		final String response = deviceServiceImpl.deleteDevice(ConstantsTest.DEVICE_ID);
		assertEquals(response, ConstantsTest.DEVICE_DELETED);
		fail();
	}

	@Test
	public void deleteDeviceInternalServerErrorTestError() throws RmmException {
		Mockito.when(deviceRepository.findById(ConstantsTest.DEVICE_ID)).thenReturn(Optional.of(DEVICE));

		Mockito.doThrow(RmmException.class).when(deviceRepository).deleteById(ConstantsTest.DEVICE_ID);

		final String response = deviceServiceImpl.deleteDevice(ConstantsTest.DEVICE_ID);
		assertEquals(response, ConstantsTest.DEVICE_DELETED);
		fail();
	}

}
