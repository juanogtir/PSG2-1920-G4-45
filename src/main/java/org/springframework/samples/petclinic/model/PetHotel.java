package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class PetHotel extends BaseEntity {
	
	@Valid
	@ManyToOne
	Pet pet;
	
	String info;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime initialDate;
	

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime endDate;
	
	
	
}
