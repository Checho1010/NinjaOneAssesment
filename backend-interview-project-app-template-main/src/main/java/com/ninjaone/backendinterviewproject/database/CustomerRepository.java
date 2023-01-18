package com.ninjaone.backendinterviewproject.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ninjaone.backendinterviewproject.model.Customer;

/**
 * @author CGomez
 *
 *         This class handle the persistence to the database for the entity
 *         {@link Customer}
 */
public interface CustomerRepository extends CrudRepository<Customer, String> {

	/**
	 * 
	 * @param name the of the customer to be found
	 * @return {@link Customer} the Customer entity that results from the search
	 */
	Optional<Customer> findByName(String name);

}
