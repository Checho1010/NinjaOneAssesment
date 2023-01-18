package com.ninjaone.backendinterviewproject.service;

import java.util.List;

import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;

/**
 * @author CGomez
 * 
 *         This interface provides the methods to list, delete and create
 *         Devices
 */

public interface DeviceService {

	/**
	 * 
	 * @param deviceId id of the Device to be found
	 * @return {@link DeviceDTO} Rest object that results from the search
	 * @throws RmmException
	 */
	DeviceDTO getDeviceById(String deviceId) throws RmmException;

	/**
	 * 
	 * @return list of all the registered {@link DeviceDTO}
	 * @throws RmmException
	 */
	public List<DeviceDTO> getDevices() throws RmmException;

	/**
	 * 
	 * @param deviceId id of the Device to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	public String deleteDevice(String deviceId) throws RmmException;

	/**
	 * 
	 * @param deviceResponse a REST object that represents the Device entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	String createDevice(DeviceDTO deviceResponse) throws RmmException;

}
