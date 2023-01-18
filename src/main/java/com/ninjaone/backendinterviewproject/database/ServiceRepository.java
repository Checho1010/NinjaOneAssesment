package com.ninjaone.backendinterviewproject.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Service;

/**
 * @author CGomez
 *
 *         This class handle the persistence to the database for the entity
 *         {@link Service}
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {

	/**
	 * 
	 * @param description description of the service to be found
	 * @param type        system type of the service to be found
	 * @return {@link Service} the Service entity that results from the search
	 */
	Optional<Service> findByDescriptionAndType(String description, String type);
}
