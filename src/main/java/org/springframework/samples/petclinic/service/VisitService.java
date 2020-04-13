package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataVisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitService {

	@Autowired
	private VisitRepository visitRepo;
	
	@Autowired
	private SpringDataVisitRepository dataVisitRepo;
	
	@Transactional(readOnly=true)
	public Optional<Visit> findVisitbyId(int id){
		return visitRepo.findById(id);
	}
	
	@Transactional
	public void delete(final int visitId) {
		Visit visit = new Visit();
		if(this.visitRepo.findById(visitId).isPresent()) {
			 visit = this.visitRepo.findById(visitId).get();
		}
		this.visitRepo.delete(visit);
	}
	
	@Transactional
	public void save(Visit visit) {
		visitRepo.save(visit);
	}
	
	
	@Transactional
	public Iterable<Visit> findAll(Pet pet){
		return visitRepo.findAll();
	}
	
}
