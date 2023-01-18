package com.ninjaone.backendinterviewproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.exceptions.EntityNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.InternalServerErrorException;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.utils.Constants;
import com.ninjaone.backendinterviewproject.utils.TypeEnum;

/**
 * @author CGomez
 *
 */
@Service
public class DeviceServiceImpl implements DeviceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Autowired
	DeviceRepository deviceRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * 
	 * @param deviceId id of the Device to be found
	 * @return {@link DeviceDTO} Rest object that results from the search
	 * @throws RmmException
	 */
	public DeviceDTO getDeviceById(String deviceId) throws RmmException {
		return modelMapper.map(getDeviceEntity(deviceId), DeviceDTO.class);
	}

	/**
	 * 
	 * @return list of all the registered {@link DeviceDTO}
	 * @throws RmmException
	 */
	@Override
	public List<DeviceDTO> getDevices() throws RmmException {
		final List<Device> devicesEntity = (List<Device>) deviceRepository.findAll();
		return devicesEntity.stream().map(service -> modelMapper.map(service, DeviceDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Local method to invoke the {@link DeviceRepository} object
	 * 
	 * @param deviceId id of the Service to be found
	 * @return {@link Device}
	 * @throws RmmException
	 */
	private Device getDeviceEntity(String deviceId) throws RmmException {
		return deviceRepository.findById(deviceId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.DEVICE_ID_NOT_FOUND_IN_GET_ENTITY,
						Constants.DEVICE_ID_NOT_FOUND_IN_GET_ENTITY));
	}

	/**
	 * 
	 * @param deviceId id of the Device to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	public String deleteDevice(String deviceId) {
		deviceRepository.findById(deviceId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.DEVICE_ID_NOT_FOUND_IN_DELETE_ENTITY,
						Constants.DEVICE_ID_NOT_FOUND_IN_DELETE_ENTITY));
		try {
			deviceRepository.deleteById(deviceId);
		} catch (RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " deviceId: " + deviceId, e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return Constants.DEVICE_DELETED;
	}

	/**
	 * 
	 * @param deviceResponse a REST object that represents the Device entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	public String createDevice(DeviceDTO deviceResponse) {
		if (deviceRepository.findBySystemNameAndType(deviceResponse.getSystemName(), deviceResponse.getType())
				.isPresent()) {
			throw new EntityNotFoundException(Constants.DEVICE_ALREADY_EXIST, Constants.DEVICE_ALREADY_EXIST);
		}

		// Validate that the given type is allowed
		if (!TypeEnum.isValidCode(deviceResponse.getType())) {
			throw new EntityNotFoundException(Constants.DEVICE_TYPE_INVALID_IN_CREATE_DEVICE,
					Constants.DEVICE_TYPE_INVALID_IN_CREATE_DEVICE);
		}
		final Device device = new Device();
		device.setSystemName(deviceResponse.getSystemName());
		device.setType(deviceResponse.getType());
		try {
			deviceRepository.save(device);
		} catch (final RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " device system name: " + deviceResponse.getSystemName(), e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return device.getId();
	}

}
