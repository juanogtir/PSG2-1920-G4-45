
package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {

	private DonationRepository	donationRepository;

	private CauseRepository		causeRepository;

	private OwnerRepository		ownerRepository;


	@Autowired
	public DonationService(final DonationRepository donationRepository, final CauseRepository causeRepository, final OwnerRepository ownerRepository) {

		this.donationRepository = donationRepository;
		this.causeRepository = causeRepository;
		this.ownerRepository = ownerRepository;
	}

	@Transactional(readOnly = true)
	public Optional<Donation> findDonationbyId(final int id) {
		return this.donationRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "donations")
	public Iterable<Donation> findDonations() throws DataAccessException {
		return this.donationRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Iterable<Owner> findOwners() throws DataAccessException {
		return this.ownerRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Cause findCauseById(final int causeId) throws DataAccessException {
		return this.causeRepository.findById(causeId);
	}

	@Transactional
	public void saveDonation(final Donation donation) throws DataAccessException {
		this.donationRepository.save(donation);
	}

}
