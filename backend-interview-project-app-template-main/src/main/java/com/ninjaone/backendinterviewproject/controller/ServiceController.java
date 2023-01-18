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

import com.ninjaone.backendinterviewproject.dto.response.ServiceDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez this class enables the endpoints for Services
 *
 */
@RestController
@CrossOrigin(origins = Constants.PATH_AND_PORT_ALLOWED_CLIENT)
@RequestMapping(Constants.ENDPOINT_PATH_RMM + Constants.ENDPOINT_PATH_VERSION + Constants.ENDPOINT_PATH_SERVICE)
public class ServiceController {

	@Autowired
	ServiceService serviceService;

	/**
	 * Endpoint to find a service by a given id
	 * 
	 * @param id of the Service to be found
	 * @return response of {@link ServiceDTO}
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<ServiceDTO> getServiceById(@PathVariable String id) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceService.getServiceById(id));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	/**
	 * Endpoint to find all the services
	 * 
	 * @return response of a list of {@link ServiceDTO}
	 * @throws RmmException
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<List<ServiceDTO>> getServices() throws RmmException {
		return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
				serviceService.getServices());
	}

	/**
	 * Endpoint to delete a Service
	 * 
	 * @param serviceId of the Service to be deleted
	 * @return a String response of the method
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> deleteService(@RequestParam String serviceId) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceService.deleteService(serviceId));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	/**
	 * Endpoint to create a Service
	 * 
	 * @param serviceDTO REST object that represents the Service entity
	 * @return a String response of the method
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> createService(@RequestBody ServiceDTO serviceDTO) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceService.createService(serviceDTO));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}
}
