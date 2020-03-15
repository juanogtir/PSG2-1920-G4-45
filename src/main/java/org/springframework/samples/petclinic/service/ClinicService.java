/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
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

	private PetRepository petRepository;

	private VetRepository vetRepository;

	private OwnerRepository ownerRepository;

	private VisitRepository visitRepository;
	
	private SpecialtyRepository		specialtyRepository;
	
	private SpringDataPetRepository dataPetRepository;
	


	@Autowired
	public ClinicService(final PetRepository petRepository, final VetRepository vetRepository, final OwnerRepository ownerRepository,
			final VisitRepository visitRepository, final SpringDataPetRepository dataPetRepository, final SpecialtyRepository specialtyRepository) {
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.dataPetRepository = dataPetRepository;
		this.specialtyRepository = specialtyRepository;
	
	}

	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException {
		return this.petRepository.findPetTypes();
	}

	@Transactional(readOnly = true)
	public Owner findOwnerById(int id) throws DataAccessException {
		return this.ownerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
		return this.ownerRepository.findByLastName(lastName);
	}

	@Transactional
	public void saveOwner(Owner owner) throws DataAccessException {
		this.ownerRepository.save(owner);
	}

	@Transactional
	public void saveVisit(Visit visit) throws DataAccessException {
		this.visitRepository.save(visit);
	}
	
	@Transactional
	public void saveVet(Vet vet) throws DataAccessException {
		this.vetRepository.save(vet);
	}

	@Transactional(readOnly = true)
	public Pet findPetById(int id) throws DataAccessException {
		return this.petRepository.findById(id);
	}

	@Transactional
	public void savePet(Pet pet) throws DataAccessException {
		this.petRepository.save(pet);
	}
	@Transactional
	public void removePet(Pet pet) throws DataAccessException{
		this.dataPetRepository.delete(pet.getId());
		
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "vets")
	public Collection<Vet> findVets() throws DataAccessException {
		return this.vetRepository.findAll();
	}

	public Collection<Visit> findVisitsByPetId(int petId) {
		return this.visitRepository.findByPetId(petId);
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
	
	public Collection<Specialty> findVetSpecialities() {
		return this.vetRepository.findVetSpecialities();
	}
	
	@Transactional(readOnly = true)
	public Vet findVetById(final int id) throws DataAccessException {
		return this.vetRepository.findById(id);
	}

}
