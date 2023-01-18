package com.ninjaone.backendinterviewproject.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Device;

/**
 * @author CGomez
 *
 *         This class handle the persistence to the database for the entity
 *         {@link Device}
 */

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {

	/**
	 * 
	 * @param systemName system name of the device to be found
	 * @param type       system type of the device to be found
	 * @return {@link Device} the Device entity that results from the search
	 */
	Optional<Device> findBySystemNameAndType(String systemName, String type);
}
