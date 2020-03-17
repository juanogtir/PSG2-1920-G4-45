package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataVetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VetService {

	@Autowired
	private VetRepository vetRepo;
	
	@Autowired
	private SpringDataVetRepository dataVetRepository;

	
	@Transactional(readOnly=true)
	public Vet findOwnerbyId(int id){
		return vetRepo.findById(id);
	}
	
	@Transactional
	public void removeVet(final Vet vet) throws DataAccessException {
		this.dataVetRepository.delete(vet.getId());
	}
}
