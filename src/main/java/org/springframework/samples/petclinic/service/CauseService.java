package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataCauseRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataVetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {

	@Autowired
	private CauseRepository causeRepo;
	
	@Autowired
	private SpringDataCauseRepository dataCauseRepository;

	
	@Transactional(readOnly=true)
	public Cause findCausebyId(int id){
		return causeRepo.findById(id);
	}
	
	@Transactional(readOnly = true)
	@Cacheable(value = "causes")
	public Collection<Cause> findCauses() throws DataAccessException {
		return this.causeRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public Integer totalAmountOfDonationsForCause(Integer causeId){
		return causeRepo.totalAmountOfDonationsForCause(causeId);
	}
	
}
