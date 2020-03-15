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

package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.service.ClinicService;

import lombok.Data;

/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

	@ManyToMany(fetch = FetchType.EAGER)
<<<<<<< HEAD
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private List<Specialty> specialties;
=======
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git

<<<<<<< HEAD
	protected List<Specialty> getSpecialtiesInternal() {
=======

	protected Set<Specialty> getSpecialtiesInternal() {
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git
		if (this.specialties == null) {
			this.specialties = new ArrayList<>();
		}
		return this.specialties;
	}

<<<<<<< HEAD
	protected void setSpecialtiesInternal(final List<Specialty> specialties) {
=======
	protected void setSpecialtiesInternal(final Set<Specialty> specialties) {
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git
		this.specialties = specialties;
	}

	@XmlElement
	public List<Specialty> getSpecialties() {
		List<Specialty> sortedSpecs = new ArrayList<>(this.getSpecialtiesInternal());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}

	public int getNrOfSpecialties() {
		return this.getSpecialtiesInternal().size();
	}

<<<<<<< HEAD
	public void addSpecialty(Specialty specialty) {
		this.getSpecialtiesInternal().add(specialty);
	}
	
	public void addSpecialties(List<Specialty> specialties) {
//		for(Specialty s : specialties) {
//			this.getSpecialtiesInternal().add(s);
//		}
		this.setSpecialtiesInternal(specialties);
	}
	
//	public void addSpecialties(String[] specialtiesNamesArray) {
//		for(String s : specialtiesNamesArray) {
//			SpecialtyRepository specialtyRepo;
//			Collection<Specialty> spec=specialtyRepo.findAll();
//			for (Specialty specialty : spec) {
//				if (specialty.getName().equals(s)) {
//					this.getSpecialtiesInternal().add(specialty);
//				}
//			}
//			
//		}
//	}
	
	public void deleteSpecialty(final Specialty specialty) {
		this.getSpecialtiesInternal().remove(specialty);
	}
	
	public void deleteAllSpecialties() {
		this.specialties = new ArrayList<>();
=======
	public void addSpecialty(final Specialty specialty) {
		this.getSpecialtiesInternal().add(specialty);
	}

	public void deleteSpecialty(final Specialty specialty) {
		this.getSpecialtiesInternal().remove(specialty);
	}

	public void deleteAllSpecialties() {
		this.specialties = new HashSet<>();
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git
	}

}
