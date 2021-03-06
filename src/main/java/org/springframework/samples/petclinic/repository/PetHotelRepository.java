package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.transaction.annotation.Transactional;

public interface PetHotelRepository extends CrudRepository<PetHotel, Integer>{
	
	@Query("SELECT ph FROM PetHotel ph WHERE ph.id = ?1")
	PetHotel findPetHotelById(int id);

	@Transactional
	@Query("SELECT ph FROM PetHotel ph WHERE ph.pet.id = ?1")
	List<PetHotel> findPetHotelsByPetId(int id);
	
}
