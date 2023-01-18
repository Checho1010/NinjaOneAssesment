package com.ninjaone.backendinterviewproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.response.ServiceDTO;
import com.ninjaone.backendinterviewproject.exceptions.EntityNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.InternalServerErrorException;
import com.ninjaone.backendinterviewproject.exceptions.RmmException;
//import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import com.ninjaone.backendinterviewproject.utils.Constants;
import com.ninjaone.backendinterviewproject.utils.TypeEnum;

/**
 * @author CGomez this class implements the methods for {@link ServiceService}
 */

@Service()
public class ServiceServiceImpl implements ServiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceServiceImpl.class);

	@Autowired
	ServiceRepository serviceRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * 
	 * @param serviceId id of the Service to be found
	 * @return {@link ServiceDTO} Rest object that results from the search
	 * @throws RmmException
	 */
	@Override
	public ServiceDTO getServiceById(String serviceId) throws RmmException {
		return modelMapper.map(getServiceEntity(serviceId), ServiceDTO.class);
	}

	/**
	 * 
	 * @return list of all the registered {@link ServiceDTO}
	 * @throws RmmException
	 */
	@Override
	public List<ServiceDTO> getServices() throws RmmException {
		final List<com.ninjaone.backendinterviewproject.model.Service> servicesEntity = (List<com.ninjaone.backendinterviewproject.model.Service>) serviceRepository
				.findAll();
		return servicesEntity.stream().map(service -> modelMapper.map(service, ServiceDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Local method to invoke the {@link ServiceRepository} object
	 * 
	 * @param serviceId id of the Service to be found
	 * @return {@link Service}
	 * @throws RmmException
	 */
	private com.ninjaone.backendinterviewproject.model.Service getServiceEntity(String serviceId) throws RmmException {
		return serviceRepository.findById(serviceId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ID_NOT_FOUND_IN_GET_ENTITY,
						Constants.SERVICE_ID_NOT_FOUND_IN_GET_ENTITY));
	}

	/**
	 * 
	 * @param serviceId id of the Service to be deleted
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	public String deleteService(String serviceId) {
		serviceRepository.findById(serviceId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.SERVICE_ID_NOT_FOUND_IN_DELETE_ENTITY,
						Constants.SERVICE_ID_NOT_FOUND_IN_DELETE_ENTITY));
		try {
			serviceRepository.deleteById(serviceId);
		} catch (RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " serviceId: " + serviceId, e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return Constants.SERVICE_DELETED;

	}

	/**
	 * 
	 * @param serviceResponse a REST object that represents the Service entity
	 * @return a String label about the execution of the method
	 * @throws RmmException
	 */
	@Override
	public String createService(ServiceDTO serviceResponse) {
		if (serviceRepository.findByDescriptionAndType(serviceResponse.getDescription(), serviceResponse.getType())
				.isPresent()) {
			throw new EntityNotFoundException(Constants.SERVICE_ALREADY_EXIST, Constants.SERVICE_ALREADY_EXIST);
		}

		// Validate that the given type is allowed
		if (!TypeEnum.isValidCode(serviceResponse.getType())) {
			throw new EntityNotFoundException(Constants.SERVICE_TYPE_INVALID_IN_CREATE_SERVICE,
					Constants.SERVICE_TYPE_INVALID_IN_CREATE_SERVICE);
		}
		final com.ninjaone.backendinterviewproject.model.Service service = new com.ninjaone.backendinterviewproject.model.Service();
		service.setDescription(serviceResponse.getDescription());
		service.setCost(serviceResponse.getCost());
		service.setType(serviceResponse.getType());
		try {
			serviceRepository.save(service);
		} catch (final RmmException e) {
			LOGGER.error(Constants.INTERNAL_SERVER_ERROR + " service description: " + serviceResponse.getDescription(), e);
			throw new InternalServerErrorException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
		}
		return service.getId();
	}

}
