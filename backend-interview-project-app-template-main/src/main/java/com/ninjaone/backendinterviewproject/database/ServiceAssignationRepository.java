package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ninjaone.backendinterviewproject.model.ServiceAssignation;

/**
 * @author CGomez
 *
 *         This interface provides the persistence to the database for the
 *         entity {@link ServiceAssignation}
 */
public interface ServiceAssignationRepository extends CrudRepository<ServiceAssignation, String> {
	/**
	 * 
	 * @param serviceId  the service id of the service assignation to be found
	 * @param deviceId   the device id of the service assignation to be found
	 * @param customerId the customer id of the service assignation to be found
	 * @return {@link ServiceAssignation} the ServiceAssignation entity that results
	 *         from the search
	 */
	Optional<ServiceAssignation> findByServiceIdAndDeviceIdAndCustomerId(String serviceId, String deviceId,
			String customerId);

	/**
	 * 
	 * @param customerId the customer id of the service assignations to be found
	 * @return a list of {@link ServiceAssignation} that results from the search
	 */
	Optional<List<ServiceAssignation>> findAllByCustomerId(String customerId);
}
