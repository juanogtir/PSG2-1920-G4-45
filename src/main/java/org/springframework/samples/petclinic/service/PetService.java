package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepo;
	
	@Transactional(readOnly=true)
	public Pet findPetbyId(int id){
		return petRepo.findById(id);
	}
	
	@Transactional
	public void save(Pet pet) {
		petRepo.save(pet);
	}
	
	
}
