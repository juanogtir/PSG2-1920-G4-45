<<<<<<< HEAD
/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Specialty;

/**
 * Repository class for <code>Vet</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data See here:
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface SpecialtyRepository {

	/**
	 * Retrieve all <code>Specialty</code>s from the data store.
	 *
	 * @return a <code>Collection</code> of <code>Specialty</code>s
	 */
	Collection<Specialty> findAll() throws DataAccessException;

	/**
	 * Retrieve a <code>Specialty</code> from the data store by id.
	 *
	 * @param id
	 *            the id to search for
	 * @return the <code>Specialty</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 *             if not found
	 */
	Specialty findById(int id) throws DataAccessException;

	Specialty findSpecialtiesById(Integer id) throws DataAccessException;

	/**
	 * Retrieve all <code>Specialty</code>s from a vet of the data store.
	 *
	 * @param id
	 *
	 * @return a <code>Collection</code> of <code>Specialty</code>s
	 */
	Collection<Specialty> findByVetId(int id) throws DataAccessException;

	/**
	 * Save a <code>Specialty</code> to the data store, either inserting or updating it.
	 *
	 * @param vet
	 *            the <code>Specialty</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(Specialty vet) throws DataAccessException;

	/**
	 * Deletes a <code>Specialty</code> from the data store by id.
	 *
	 * @param id
	 *            the id to search for
	 * @return the <code>Specialty</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 *             if not found
	 */
	void deleteById(int id);

}
=======
/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Specialty;

/**
 * Repository class for <code>Vet</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data See here:
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface SpecialtyRepository {

	/**
	 * Retrieve all <code>Specialty</code>s from the data store.
	 *
	 * @return a <code>Collection</code> of <code>Specialty</code>s
	 */
	Collection<Specialty> findAll() throws DataAccessException;

	/**
	 * Retrieve a <code>Specialty</code> from the data store by id.
	 *
	 * @param id
	 *            the id to search for
	 * @return the <code>Specialty</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 *             if not found
	 */
	Specialty findById(int id) throws DataAccessException;

	Specialty findSpecialtiesById(Integer id) throws DataAccessException;

	/**
	 * Retrieve all <code>Specialty</code>s from a vet of the data store.
	 *
	 * @param id
	 *
	 * @return a <code>Collection</code> of <code>Specialty</code>s
	 */
	Collection<Specialty> findByVetId(int id) throws DataAccessException;

	/**
	 * Save a <code>Specialty</code> to the data store, either inserting or updating it.
	 *
	 * @param vet
	 *            the <code>Specialty</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(Specialty vet) throws DataAccessException;

	/**
	 * Deletes a <code>Specialty</code> from the data store by id.
	 *
	 * @param id
	 *            the id to search for
	 * @return the <code>Specialty</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 *             if not found
	 */
	void deleteById(int id);

}
>>>>>>> createVet
