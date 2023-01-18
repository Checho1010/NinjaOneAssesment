package com.ninjaone.backendinterviewproject.utils;

/**
 * @author CGomez This class storages constants used through the application
 *
 */
public class Constants {

	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final String SUCCESS = "Success";
	public static final String OK = "OK";

	// ** ENDPOINTS **//
	public static final String PATH_AND_PORT_ALLOWED_CLIENT = "http://localhost:4200";
	public static final String ENDPOINT_PATH_RMM = "/rmm";
	public static final String ENDPOINT_PATH_VERSION = "/v1";
	public static final String ENDPOINT_PATH_DEVICE = "/device";
	public static final String ENDPOINT_PATH_SERVICE = "/service";
	public static final String ENDPOINT_PATH_CUSTOMER = "/customer";
	public static final String ENDPOINT_PATH_SERVICE_ASSIGNATION = "/serviceAssignation";
	public static final String ENDPOINT_PATH_COST_CALCULATION = "/costCalculation";
	public static final String ENDPOINT_PATH_CREATE = "/create";
	public static final String ENDPOINT_PATH_DELETE = "/delete";
	public static final String ENDPOINT_PATH_ALL = "/all";
	public static final String ENDPOINT_PATH_ID = "/{id}";

	// ** DEVICES **//
	public static final String DEVICE_ID_NOT_FOUND_IN_GET_ENTITY = "DEVICE_ID_NOT_FOUND_IN_GET_ENTITY";
	public static final String DEVICE_ID_NOT_FOUND_IN_DELETE_ENTITY = "DEVICE_ID_NOT_FOUND_IN_DELETE_ENTITY";
	public static final String DEVICE_DELETED = "DEVICE DELETED";
	public static final String DEVICE_ALREADY_EXIST = "DEVICE_ALREADY_EXIST";
	public static final String DEVICE_TYPE_INVALID_IN_CREATE_DEVICE = "DEVICE_TYPE_INVALID_IN_CREATE_DEVICE";

	// ** SERVICES **//
	public static final String SERVICE_ID_NOT_FOUND_IN_GET_ENTITY = "SERVICE_ID_NOT_FOUND_IN_GET_ENTITY";
	public static final String SERVICE_ID_NOT_FOUND_IN_DELETE_ENTITY = "SERVICE_ID_NOT_FOUND_IN_DELETE_ENTITY";
	public static final String SERVICE_DELETED = "SERVICE DELETED";
	public static final String SERVICE_ALREADY_EXIST = "SERVICE_ALREADY_EXIST";
	public static final String SERVICE_TYPE_INVALID_IN_CREATE_SERVICE = "SERVICE_TYPE_INVALID_IN_CREATE_SERVICE";

	// ** CUSTOMERS **//
	public static final String CUSTOMER_ID_NOT_FOUND_IN_GET_ENTITY = "CUSTOMER_ID_NOT_FOUND_IN_GET_ENTITY";
	public static final String CUSTOMER_ID_NOT_FOUND_IN_DELETE_ENTITY = "CUSTOMER_ID_NOT_FOUND_IN_DELETE_ENTITY";
	public static final String CUSTOMER_DELETED = "CUSTOMER DELETED";
	public static final String CUSTOMER_ALREADY_EXIST = "CUSTOMER_ALREADY_EXIST";

	// ** SERVICE_ASSIGNATION **//
	public static final String SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_GET_ENTITY = "SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_GET_ENTITY";
	public static final String SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_DELETE_ENTITY = "SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_DELETE_ENTITY";
	public static final String SERVICE_ASSIGNATION_DELETED = "SERVICE_ASSIGNATION DELETED";
	public static final String SERVICE_ASSIGNATION_ALREADY_EXIST = "SERVICE_ASSIGNATION_ALREADY_EXIST";

	public static final String SERVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT = "SERVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT";
	public static final String DEVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT = "DEVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT";
	public static final String CUSTOMER_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT = "CUSTOMER_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT";

	public static final String DEVICE_AND_SERVICE_TYPE_DOESNT_MATCH = "DEVICE_AND_SERVICE_TYPE_DOESNT_MATCH";

	public static final String SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID = "SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID";

	// ** COST CALCULATION **//
	public static final String CUSTOMER_ID_NOT_FOUND_IN_COST_CALCULATION = "CUSTOMER_ID_NOT_FOUND_IN_COST_CALCULATION";
	public static final String SERVICE_ID_NOT_FOUND_IN_COST_CALCULATION = "SERVICE_ID_NOT_FOUND_IN_COST_CALCULATION";
	public static final Long COST_PER_DEVICE = 4l;

	// ** CACHE **//
	public static final String COST_CALCULATION_CACHE = "costCalculationCache";
}
