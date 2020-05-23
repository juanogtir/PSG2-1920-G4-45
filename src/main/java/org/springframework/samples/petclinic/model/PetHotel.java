package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class PetHotel extends BaseEntity {
	
	@Valid
	@ManyToOne
	Pet pet;
	
	String info;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDateTime initialDate;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDateTime endDate;
	
	public String getMinutesEnd() {
		String s = "";
		if(endDate.getMinute()<10) {
			s = "0"+String.valueOf(endDate.getMinute());
		}else {
			s = String.valueOf(endDate.getMinute());
		}
		return s;
	}
	
	public String getMinutesInit() {
		String s = "";
		if(initialDate.getMinute()<10) {
			s = "0"+String.valueOf(initialDate.getMinute());
		}else {
			s = String.valueOf(initialDate.getMinute());
		}
		return s;
	}
	
}
