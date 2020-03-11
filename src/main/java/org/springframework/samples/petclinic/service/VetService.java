package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VetService {

	@Autowired
	private VetRepository vetRepo;
	
	@Transactional(readOnly=true)
	public Vet findOwnerbyId(int id){
		return vetRepo.findById(id).get();
	}
	
	public void delete(Vet vet) {
		vetRepo.delete(vet);
	}
}
