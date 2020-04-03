
package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Donation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DonationValidator implements Validator {

	@Override
	public void validate(final Object obj, final Errors errors) {
		Donation donation = (Donation) obj;
		if (donation.getAmount() == null || donation.getClient() == null) {
			if (donation.getAmount() == null) {
				errors.rejectValue("amount", "invalidDonation", "Must be filled");
			}

			if (donation.getClient() == null) {
				errors.rejectValue("client", "invalidClient", "Must be chosen");
			}
		}
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return Donation.class.isAssignableFrom(clazz);
	}

}
