package com.ninjaone.backendinterviewproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez this class enables the endpoints for Devices
 *
 */
@RestController
@CrossOrigin(origins = Constants.PATH_AND_PORT_ALLOWED_CLIENT)
@RequestMapping(Constants.ENDPOINT_PATH_RMM + Constants.ENDPOINT_PATH_VERSION + Constants.ENDPOINT_PATH_DEVICE)
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	/**
	 * Endpoint to find a device by a given id
	 * 
	 * @param id of the Device to be found
	 * @return response of {@link DeviceDTO}
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<DeviceDTO> getDeviceById(@PathVariable String id) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					deviceService.getDeviceById(id));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	/**
	 * Endpoint to find all the devices
	 * 
	 * @return response of a list of {@link DeviceDTO}
	 * @throws RmmException
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<List<DeviceDTO>> getDevices() throws RmmException {
		return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
				deviceService.getDevices());
	}

	/**
	 * Endpoint to delete a Device
	 * 
	 * @param deviceId of the Device to be deleted
	 * @return a String response of the method
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> deleteDevice(@RequestParam String deviceId) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					deviceService.deleteDevice(deviceId));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}

	}

	/**
	 * Endpoint to create a Device
	 * 
	 * @param deviceDTO REST object that represents the Device entity
	 * @return a String response of the method
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> createDevice(@RequestBody DeviceDTO deviceDTO) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					deviceService.createDevice(deviceDTO));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}

	}

}
