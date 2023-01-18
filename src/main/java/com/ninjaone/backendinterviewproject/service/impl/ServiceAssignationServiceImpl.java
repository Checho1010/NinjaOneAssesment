package com.ninjaone.backendinterviewproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServiceAssignationRepository;
import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.response.ServiceAssignationDTO;
import com.ninjaone.backendinterviewproject.exceptions.EntityNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.InternalServerErrorException;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceAssignation;
import com.ninjaone.backendinterviewproject.service.ServiceAssignationService;
import com.ninjaone.backendinterviewproject.utils.Constants;

/**
 * @author CGomez
 *
 */
@Service()
public class ServiceAssignationServiceImpl implements ServiceAssignationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAssignationServiceImpl.class);

	@Autowired
	ServiceAssignationRepository serviceAssignationRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	CustomerRepository customerRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * 
	 * @param serviceAssignationId id of the ServiceAssignation to be found
	 * @return {@link ServiceAssignationDTO} Rest object that results from the
	 *         search
	 * @throws RmmException
	 */
	@Override
	public ServiceAssignationDTO getServiceAssignationById(String serviceAssignationId) throws RmmException {
		return modelMapper.map(getServiceAssignationEntity(serviceAssignationId), ServiceAssignationDTO.class);
	}

	/**
	 * 
	 * @return list of all the registered {@link ServiceAssignationDTO}
	 * @throws RmmException
	 */
	@Override
	public List<ServiceAssignationDTO> getServiceAssignations() throws RmmException {
		final List<ServiceAssignation> serviceAssignationsEntity = (List<ServiceAssignation>) serviceAssignationRepository
				.findAll();
		return serviceAssignationsEntity.stream().map(service -> modelMapper.map(service, ServiceAssignationDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Local method to invoke the {@link serviceAssignationRepository} object
	 * 
	 * @param serviceAssignationId id of the ServiceAssignation to be found
	 * @return {@link ServiceAssignation}
	 * @throws RmmException
	 */
	private ServiceAssignation getServiceAssignationEntity(String serviceAssignationId) throws RmmException {
		return serviceAssignationRepository.findById(serviceAssignationId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_GET_ENTITY,
						Constants.SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_GET_ENTITY));
	}

	/**
	 * 
	 * @param serviceAssignationId id of the ServiceAsignation to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	@CacheEvict(value = Constants.COST_CALCULATION_CACHE, allEntries = true)
	public String deleteServiceAssignation(String serviceAssignationId) throws RmmException {
		serviceAssignationRepository.findById(serviceAssignationId).orElseThrow(
				() -> new EntityNotFoundException(Constants.SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_DELETE_ENTITY,
						Constants.SERVICE_ASSIGNATION_ID_NOT_FOUND_IN_DELETE_ENTITY));
		try {
			serviceAssignationRepository.deleteById(serviceAssignationId);
		} catch (RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " serviceAssignationId: " + serviceAssignationId, e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return Constants.SERVICE_ASSIGNATION_DELETED;
	}

	/**
	 * 
	 * @param serviceAssignationResponse a REST object that represents the
	 *                                   ServiceAssignation entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	@CacheEvict(value = Constants.COST_CALCULATION_CACHE, allEntries = true)
	public String createServiceAssignation(ServiceAssignationDTO serviceAssignationResponse) throws RmmException {

		// validate if the service assignation already exists for the customer
		if (serviceAssignationRepository
				.findByServiceIdAndDeviceIdAndCustomerId(serviceAssignationResponse.getServiceId(),
						serviceAssignationResponse.getDeviceId(), serviceAssignationResponse.getCustomerId())// serviceAssignationResponse.getDeviceId(),
																												// serviceAssignationResponse.getCustomerId())
				.isPresent()) {
			throw new EntityNotFoundException(Constants.SERVICE_ASSIGNATION_ALREADY_EXIST,
					Constants.SERVICE_ASSIGNATION_ALREADY_EXIST);
		}

		// obtain the service or throwing FOUND_EXCEPTION if it doesn't exists
		final com.ninjaone.backendinterviewproject.model.Service service = serviceRepository
				.findById(serviceAssignationResponse.getServiceId())
				.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT,
						Constants.SERVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT));

		// obtain the device or throwing FOUND_EXCEPTION if it doesn't exists
		final Device device = deviceRepository.findById(serviceAssignationResponse.getDeviceId())
				.orElseThrow(() -> new EntityNotFoundException(Constants.DEVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT,
						Constants.DEVICE_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT));

		// validate that the device and service are of the same type
		if (!service.getType().equals(device.getType())) {
			throw new InternalServerErrorException(Constants.DEVICE_AND_SERVICE_TYPE_DOESNT_MATCH,
					Constants.DEVICE_AND_SERVICE_TYPE_DOESNT_MATCH);
		}

		// obtain the customer or throwing FOUND_EXCEPTION if it doesn't exists
		final Customer customer = customerRepository.findById(serviceAssignationResponse.getCustomerId()).orElseThrow(
				() -> new EntityNotFoundException(Constants.CUSTOMER_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT,
						Constants.CUSTOMER_ID_NOT_FOUND_IN_SAVE_SERVICE_ASSIGMENT));

		final ServiceAssignation serviceAssignation = new ServiceAssignation();
		serviceAssignation.setService(service);
		serviceAssignation.setDevice(device);
		serviceAssignation.setCustomer(customer);
		serviceAssignation.setQuantity(serviceAssignationResponse.getQuantity());
		try {
			serviceAssignationRepository.save(serviceAssignation);
		} catch (RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " service assignation customer id: " + serviceAssignationResponse.getCustomerId(), e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return serviceAssignation.getId();
	}

	/**
	 * 
	 * @param customerId id of the Customer of whom the method will return his
	 *                   service assignations
	 * @returnlist of the registered {@link ServiceAssignationDTO} that belongs to
	 *             the given customer
	 * @throws RmmException
	 */
	@Override
	public List<ServiceAssignationDTO> getServiceAssignationsByCustomerId(String customerId) throws RmmException {
		final List<ServiceAssignation> serviceAssignationsEntity = (List<ServiceAssignation>) serviceAssignationRepository
				.findAllByCustomerId(customerId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID,
						Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID));
		if (serviceAssignationsEntity.size() == 0) {
			throw new EntityNotFoundException(Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID,
					Constants.SERVICE_ASSIGNATIONS_NOT_FOUND_BY_CUSTOMER_ID);
		}
		return serviceAssignationsEntity.stream().map(service -> modelMapper.map(service, ServiceAssignationDTO.class))
				.collect(Collectors.toList());
	}

}
