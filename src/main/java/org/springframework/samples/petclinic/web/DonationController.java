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

package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class DonationController {

	@Autowired
	private final DonationService	donationService;

	@Autowired
	private final OwnerService		ownerService;

	@Autowired
	private final CauseService		causeService;

	private static final String		VIEWS_DONATION_CREATE_FORM	= "donations/createDonationForm";


	@Autowired
	public DonationController(final DonationService donationService, final OwnerService ownerService, final CauseService causeService) {
		this.donationService = donationService;
		this.ownerService = ownerService;
		this.causeService = causeService;
	}

	public void insertOwners(final Map<String, Object> model, final Donation donation, final int causeId) {
		Iterable<Owner> owners = this.ownerService.findAll();
		model.put("owners", owners);
	}

	@GetMapping(value = "/causes/{causeId}/donate")
	public String initCreationForm(@PathVariable("causeId") final int causeId, final ModelMap model) {
		Donation donation = new Donation();
		this.insertOwners(model, donation, causeId);
		model.put("donation", donation);
		return DonationController.VIEWS_DONATION_CREATE_FORM;
	}

	@PostMapping(value = "/causes/{causeId}/donate")
	public String processCreationForm(@PathVariable("causeId") final int causeId, @Valid final Donation donation, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			this.insertOwners(model, donation, causeId);
			model.put("donation", donation);
			return DonationController.VIEWS_DONATION_CREATE_FORM;
		} else {
			LocalDate donationDate = LocalDate.now();
			Cause cause = this.causeService.findCausebyId(causeId);
			donation.setDonationDate(donationDate);
			donation.setCause(cause);
			//cause.getDonations().add(donation);
			this.donationService.saveDonation(donation);

			return "redirect:/causes/" + causeId;
		}
	}

	@InitBinder("donation")
	public void initPetBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new DonationValidator());
	}

}
