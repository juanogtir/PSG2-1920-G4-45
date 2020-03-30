package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/pet-hotels")
public class PetHotelController {
	
	private static final String VIEWS_PETHOTEL_CREATE_FORM = "pet-hotel/createPetHotelForm";

	private final ClinicService clinicService;
	
	@Autowired
	public PetHotelController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@ModelAttribute("pet")
	public Pet findPet(@PathVariable("petId") int petId) {
		return this.clinicService.findPetById(petId);
	}

	@ModelAttribute("petHotel")
	public PetHotel loadPetHotelWithPet(@PathVariable("petId") int petId) {
		PetHotel petHotel = new PetHotel();
		petHotel.setPet(this.clinicService.findPetById(petId));
		return petHotel;
	}
	
	@InitBinder("petHotel")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetHotelValidator());
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Pet pet, ModelMap model) {
		PetHotel petHotel = new PetHotel();
		petHotel.setPet(pet);
		model.put("petHotel", petHotel);
		return VIEWS_PETHOTEL_CREATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(Pet pet,@Valid PetHotel petHotel, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			model.put("petHotel", petHotel);
			return VIEWS_PETHOTEL_CREATE_FORM;
		} 
		
		Pet mascota = petHotel.getPet();
		Collection<PetHotel> citas = this.clinicService.findPetHotelsByPetId(mascota.getId());
		boolean noCoincide = citas.stream()
				.allMatch(c->(c.getInitialDate().compareTo(petHotel.getEndDate())>0)
				|| (c.getEndDate().compareTo(petHotel.getInitialDate())<0));
		
		if (!noCoincide) {
			result.rejectValue("endDate", "invalid", "La mascota ya tiene una reserva que coincide con la franja horaria indicada");			
			return VIEWS_PETHOTEL_CREATE_FORM;
			
		} else {
			petHotel.setPet(pet);
			this.clinicService.savePetHotel(petHotel);
			return "redirect:/owners/{ownerId}/pets/{petId}/pet-hotels/list";
		}
	}

	@GetMapping(value = "/list")
	public String listPetHotels(@PathVariable int petId, Map<String, Object> model) {
		model.put("petHotels", this.clinicService.findPetHotelsByPetId(petId));
		return "pet-hotel/petHotelList";
	}



	@GetMapping(value="/delete/{petHotelId}")
	public String borrarPetHotel(@PathVariable("petHotelId") int petHotelId,@PathVariable("ownerId") int ownerId,
			@PathVariable("petId") int petId, ModelMap modelMap) {
		String view = "redirect:/owners/{ownerId}/pets/{petId}/pet-hotels/list";
		PetHotel petHotel = this.clinicService.findPetHotelById(petHotelId);
		 if(petHotel!=null) {
			 this.clinicService.removePetHotel(petHotel);	
			 modelMap.addAttribute("message","PetHotel succesfully deleted!");
		 }else {
			 modelMap.addAttribute("message","PetHotel not found!");
		 }
		 return view;
	}

	
}
