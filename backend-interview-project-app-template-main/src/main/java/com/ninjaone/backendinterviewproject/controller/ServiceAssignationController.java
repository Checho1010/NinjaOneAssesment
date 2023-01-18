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

import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.ServiceAssignationService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez
 *
 */
@RestController
@CrossOrigin(origins = Constants.PATH_AND_PORT_ALLOWED_CLIENT)
@RequestMapping(Constants.ENDPOINT_PATH_RMM + Constants.ENDPOINT_PATH_VERSION
		+ Constants.ENDPOINT_PATH_SERVICE_ASSIGNATION)
public class ServiceAssignationController {

	@Autowired
	ServiceAssignationService serviceAssignationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<ServiceAssignationDTO> getServiceAssignationById(@PathVariable String id) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceAssignationService.getServiceAssignationById(id));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<List<ServiceAssignationDTO>> getServiceAssignations() throws RmmException {
		return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
				serviceAssignationService.getServiceAssignations());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> deleteServiceAssignation(@RequestParam String serviceAssignationId) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceAssignationService.deleteServiceAssignation(serviceAssignationId));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<String> createServiceAssignation(@RequestBody ServiceAssignationDTO serviceAssignationDTO) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceAssignationService.createServiceAssignation(serviceAssignationDTO));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_CUSTOMER
			+ Constants.ENDPOINT_PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<List<ServiceAssignationDTO>> getServiceAssignationsByCustomerId(@PathVariable String id) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					serviceAssignationService.getServiceAssignationsByCustomerId(id));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}
	}

}
