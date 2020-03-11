package org.springframework.samples.petclinic.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepo;
	
	@Transactional(readOnly=true)
	public Owner findOwnerbyId(int id){
		return ownerRepo.findById(id);
	}
	
	public void delete(Owner owner) {
		ownerRepo.delete(owner);
	}
	
	@Transactional
	public void save(Owner owner) {
		ownerRepo.save(owner);
	}
	
	@Transactional
	public Iterable<Owner> findAll(){
		return ownerRepo.findAll();
	}
}
