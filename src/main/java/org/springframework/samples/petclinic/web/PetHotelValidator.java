package org.springframework.samples.petclinic.web;


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
			errors.rejectValue("initialDate", "invalid", "Las fechas son obligatorias y deben seguir el siguiente patrón: dd/MM/yyyy HH:mm");
	
		} else if (nuevaCita.getEndDate() == null) {
			errors.rejectValue("endDate", "invalid", "Las fechas son obligatorias y deben seguir el siguiente patrón: dd/MM/yyyy HH:mm");
			
		} else {		
			if (nuevaCita.getInitialDate() != null && !nuevaCita.getInitialDate().toString()
					.matches("^(([0][1-9])|([1-2][0-9])|([3][0-1]))(\\/)(([0][1-9])|([1][0-2]))(\\/)([0-9]{4})\\s(([0-1][0-9]|[2][0-3]))\\:([0-5][0-9])$")) {
				errors.rejectValue("initialDate", "invalid format", "La fecha debe seguir el patrón 'dd/MM/yyyy HH:mm'");
			}
			
			if (nuevaCita.getEndDate() != null && !nuevaCita.getEndDate().toString()
					.matches("^(([0][1-9])|([1-2][0-9])|([3][0-1]))(\\/)(([0][1-9])|([1][0-2]))(\\/)([0-9]{4})\\s(([0-1][0-9]|[2][0-3]))\\:([0-5][0-9])$")) {
				errors.rejectValue("endDate", "invalid format", "La fecha debe seguir el patrón 'dd/MM/yyyy HH:mm'");
			}
			
			if (nuevaCita.getInitialDate() != null && nuevaCita.getEndDate() != null) {		
				if (nuevaCita.getInitialDate().compareTo(nuevaCita.getEndDate()) >= 0) {
					errors.rejectValue("initialDate", "invalid", "La fecha de inicio debe ser anterior a la fecha final");			
				}			
			}	
		}
		
	}

}
