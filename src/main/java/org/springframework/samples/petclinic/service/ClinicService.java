/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetHotelRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataPetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicService {

	private PetRepository			petRepository;

	private VetRepository			vetRepository;

	private SpecialtyRepository		specialtyRepository;

	private OwnerRepository			ownerRepository;

	private VisitRepository visitRepository;
	
	private SpringDataPetRepository dataPetRepository;
  
	private PetHotelRepository petHotelRepository;


	public ClinicService(final PetRepository petRepository, final VetRepository vetRepository, final SpecialtyRepository specialtyRepository, final OwnerRepository ownerRepository, final VisitRepository visitRepository,
		final SpringDataPetRepository dataPetRepository, PetHotelRepository petHotelRepository) {
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.specialtyRepository = specialtyRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.dataPetRepository = dataPetRepository;
		this.petHotelRepository = petHotelRepository;

	}

	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException {
		return this.petRepository.findPetTypes();
	}

	@Transactional(readOnly = true)
	public Owner findOwnerById(final int id) throws DataAccessException {
		return this.ownerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(final String lastName) throws DataAccessException {
		return this.ownerRepository.findByLastName(lastName);
	}

	@Transactional
	public void saveOwner(final Owner owner) throws DataAccessException { 
		this.ownerRepository.save(owner);
	}

	@Transactional
	public void saveVisit(final Visit visit) throws DataAccessException {
		this.visitRepository.save(visit);
	}

	@Transactional(readOnly = true)
	public Pet findPetById(final int id) throws DataAccessException {
		return this.petRepository.findById(id);
	}

	@Transactional
	public void savePet(final Pet pet) throws DataAccessException {
		this.petRepository.save(pet);
	}
	@Transactional
	public void removePet(final Pet pet) throws DataAccessException {
		this.dataPetRepository.delete(pet);
	}

	@Transactional(readOnly = true)
	public Vet findVetById(final int id) throws DataAccessException {
		return this.vetRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Specialty> findVetSpecialities() {
		return this.vetRepository.findVetSpecialities();
	}

	@Transactional
	public void saveVet(final Vet vet) throws DataAccessException {
		this.vetRepository.save(vet);
	}

	@Transactional
	public void saveSpecialty(@Valid final Specialty specialty) {
		this.specialtyRepository.save(specialty);
	}

	@Transactional(readOnly = true)
	public Collection<Specialty> findSpecialitiesByVetId(final int vetId) {
		return this.specialtyRepository.findByVetId(vetId);
	}

	@Transactional(readOnly = true)
	public Specialty findSpecialtyById(final int specialtyId) {
		return this.specialtyRepository.findById(specialtyId);
	}

	@Transactional(readOnly = true)
	public Collection<Specialty> getAllSpecialties() {
		return this.specialtyRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Set<Specialty> findSpecialtiesById(final Integer[] ids) throws DataAccessException {
		Set<Specialty> set = new HashSet<>();
		for (Integer id : ids) {
			Specialty sp = this.specialtyRepository.findSpecialtiesById(id);
			set.add(sp);
		}
		return set;
	}

	@Transactional
	public void deleteSpecialtyById(final int specialtyId) {
		for (Vet vet : this.vetRepository.findBySpecialtyId(specialtyId)) {
			vet.deleteSpecialty(this.findSpecialtyById(specialtyId));
		}
		this.specialtyRepository.deleteById(specialtyId);
  }
  

	@Transactional(readOnly = true)
	@Cacheable(value = "vets")
	public Collection<Vet> findVets() throws DataAccessException {
		return this.vetRepository.findAll();
	}

	public Collection<Visit> findVisitsByPetId(final int petId) {
		return this.visitRepository.findByPetId(petId);
	}
	
	
	@Transactional
	public PetHotel findPetHotelById(int id) throws DataAccessException {
		return petHotelRepository.findPetHotelById(id);
	}
	
	@Transactional
	public void savePetHotel(PetHotel pethotel) throws DataAccessException {
		petHotelRepository.save(pethotel);
	}
	
	@Transactional
	public List<PetHotel> findPetHotelsByPetId(int id) throws DataAccessException {
		return petHotelRepository.findPetHotelsByPetId(id);
	}
    
	@Transactional(readOnly = true)
	public Visit findVisitById(int id) throws DataAccessException {
		Visit v = new Visit();
		if(visitRepository.findById(id).isPresent()) {
			v = visitRepository.findById(id).get();
		}
		return v; 
	}

	@Transactional
	public void removePetHotel(final PetHotel petHotel) throws DataAccessException {
		this.petHotelRepository.delete(petHotel);
	}
	
	@Transactional
	public void removePetVisits(final Pet pet) throws DataAccessException {
		List<Visit> visitas = pet.getVisits();
		visitas.clear();
	}
}
