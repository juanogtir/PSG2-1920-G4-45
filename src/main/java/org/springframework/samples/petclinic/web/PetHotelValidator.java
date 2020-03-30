package org.springframework.samples.petclinic.web;


import java.time.LocalDateTime;

import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PetHotelValidator  implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PetHotel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PetHotel nuevaCita = (PetHotel) obj;
		
		if (nuevaCita.getInitialDate() == null) {
			errors.rejectValue("initialDate", "invalid", "La fecha de inicio es obligatoria");	
		} 
		
		if (nuevaCita.getEndDate() == null) {
			errors.rejectValue("endDate", "invalid", "La fecha de fin es obligatoria");
		}
		
		if (nuevaCita.getInitialDate() != null && !nuevaCita.getInitialDate().isAfter(LocalDateTime.now())) {
			errors.rejectValue("initialDate", "invalid", "La fecha de inicio debe de ser una fecha futura");			
		}	
			
		if (nuevaCita.getInitialDate() != null && nuevaCita.getEndDate() != null 
				&& nuevaCita.getInitialDate().compareTo(nuevaCita.getEndDate()) >= 0) {		
			errors.rejectValue("endDate", "invalid", "La fecha de fin debe ser posterior a la fecha de inicio");						
		}	
		
	}

}
