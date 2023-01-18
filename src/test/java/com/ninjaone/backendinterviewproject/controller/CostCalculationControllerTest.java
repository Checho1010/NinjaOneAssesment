/**
 * 
 */
package com.ninjaone.backendinterviewproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationCostDTO;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.responses.RmmResponse;
import com.ninjaone.backendinterviewproject.service.CostCalculationService;
import com.ninjaone.backendinterviewproject.utils.ConstantsTest;

/**
 * @author CGomez
 *
 */
public class CostCalculationControllerTest {

	public static final ServiceAssignationCostDTO ASSIGNATION_COST_REST = new ServiceAssignationCostDTO();
	
	@Mock
	CostCalculationService costCalculationService;
	
	@InjectMocks
	CostCalculationController costCalculationController;
	
	@BeforeEach
	public void init() throws RmmException {
		MockitoAnnotations.openMocks(this);
		
		ASSIGNATION_COST_REST.setDevicesCost(ConstantsTest.SERVICE_ASSIGNATION_COST_DEVICES_COST);
		ASSIGNATION_COST_REST.setDevicesQuantity(ConstantsTest.SERVICE_ASSIGNATION_COST_DEVICES_QUANTITY);
		ASSIGNATION_COST_REST.setServicesCost(ConstantsTest.SERVICE_ASSIGNATION_COST_SERVICES_COST);
		ASSIGNATION_COST_REST.setServicesQuantity(ConstantsTest.SERVICE_ASSIGNATION_COST_SERVICES_QUANTITY);
		ASSIGNATION_COST_REST.setTotalCost(ConstantsTest.SERVICE_ASSIGNATION_COST_TOTAL_COST);
	}
	
	@Test
	public void calculateServiceAssignationCostByCustomerId() throws RmmException {
		Mockito.when(costCalculationService.calculateServiceAssignationCostByCustomerId(ConstantsTest.CUSTOMER_ID)).thenReturn(ASSIGNATION_COST_REST);
		
		final RmmResponse<ServiceAssignationCostDTO> response = costCalculationController.calculateServiceAssignationCostByCustomerId(ConstantsTest.CUSTOMER_ID);
		
		assertEquals(response.getStatus(), ConstantsTest.SUCCESS_STATUS);
		assertEquals(response.getCode(), ConstantsTest.SUCCESS_CODE);
		assertEquals(response.getMessage(), ConstantsTest.OK);
		assertEquals(response.getData(), ASSIGNATION_COST_REST);
	}
}
