
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;

public interface SpringDataSpecialtyRepository extends SpecialtyRepository, Repository<Specialty, Integer> {

	@Override
	@Query("SELECT vet.specialties FROM Vet vet WHERE vet.id=?1")
	Collection<Specialty> findByVetId(int id) throws DataAccessException;

	@Override
	@Query("SELECT specialty FROM Specialty specialty WHERE specialty.id =:id")
	Specialty findSpecialtiesById(@Param("id") Integer id) throws DataAccessException;

}