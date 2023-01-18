package com.ninjaone.backendinterviewproject.utils;

import java.math.BigDecimal;

/**
 * @author CGomez Constants for the objetcs used in the tests
 *
 */
public class ConstantsTest {

	// ** RESPONSE **//
	public static final String SUCCESS_STATUS = "Success";
	public static final String SUCCESS_CODE = "200 OK";
	public static final String OK = "OK";

	// ** DEVICES **//
	public static final String DEVICE_ID = "1";
	public static final String DEVICE_SYSTEM_NAME = "CENTOS";
	public static final String DEVICE_TYPE = "Linux";
	public static final String DEVICE_DELETED = "DEVICE DELETED";

	// ** DEVICES **//
	public static final String CUSTOMER_ID = "1";
	public static final String CUSTOMER_NAME = "Juan Ignacio";
	public static final int CUSTOMER_NUMBER_OF_DEVICES = 3;
	public static final String CUSTOMER_DELETED = "CUSTOMER DELETED";

	// ** DEVICES **//
	public static final String SERVICE_ID = "1";
	public static final String SERVICE_DESCRIPTION = "Super Screen Share";
	public static final BigDecimal SERVICE_COST = BigDecimal.TEN;
	public static final String SERVICE_TYPE = "Mac";
	public static final String SERVICE_DELETED = "SERVICE DELETED";

	// ** SERVICE_ASSIGNATION **//
	public static final String SERVICE_ASSIGNATION_REST_ID = "1";
	public static final String SERVICE_ASSIGNATION_REST_SERVICE_ID = "1";
	public static final String SERVICE_ASSIGNATION_REST_DEVICE_ID = "1";
	public static final String SERVICE_ASSIGNATION_REST_CUSTOMER_ID = "1";
	public static final int SERVICE_ASSIGNATION_REST_QUANTITY = 20;
	public static final String SERVICE_ASSIGNATION_DELETED = "SERVICE_ASSIGNATION DELETED";
	
	
	// ** SERVICE_ASSIGNATION_COST **//
	public static final BigDecimal SERVICE_ASSIGNATION_COST_DEVICES_COST = BigDecimal.valueOf(25l);
	public static final int SERVICE_ASSIGNATION_COST_DEVICES_QUANTITY = 10;
	public static final BigDecimal SERVICE_ASSIGNATION_COST_SERVICES_COST= BigDecimal.valueOf(30l);
	public static final int SERVICE_ASSIGNATION_COST_SERVICES_QUANTITY = 7;
	public static final BigDecimal SERVICE_ASSIGNATION_COST_TOTAL_COST = BigDecimal.valueOf(55l);

}
