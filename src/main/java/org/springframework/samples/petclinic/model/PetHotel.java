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
	
	
}
