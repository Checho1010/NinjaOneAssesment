/**
 * 
 */
package com.ninjaone.backendinterviewproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationCostDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.CostCalculationService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez this class enables the endpoints for cost calculations
 *
 */
@RestController
@CrossOrigin(origins = Constants.PATH_AND_PORT_ALLOWED_CLIENT)
@RequestMapping(Constants.ENDPOINT_PATH_RMM + Constants.ENDPOINT_PATH_VERSION
		+ Constants.ENDPOINT_PATH_COST_CALCULATION)
public class CostCalculationController {

	@Autowired
	CostCalculationService costCalculationService;

	/**
	 * response info about the costs of the customer's devices and their assignated
	 * services
	 * 
	 * @param id of the Customer
	 * @return {@link ServiceAssignationCostDTO} with the info of the costs
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = Constants.ENDPOINT_PATH_CUSTOMER
			+ Constants.ENDPOINT_PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RmmResponse<ServiceAssignationCostDTO> calculateServiceAssignationCostByCustomerId(@PathVariable String id) {
		try {
			return new RmmResponse<>(Constants.SUCCESS, String.valueOf(HttpStatus.OK), Constants.OK,
					costCalculationService.calculateServiceAssignationCostByCustomerId(id));
		} catch (RmmException e) {
			return new RmmResponse<>(e.getCode(), String.valueOf(e.getResponseCode()), e.getMessage());
		}

	}
}
