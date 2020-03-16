package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
		return this.clinicService.findOwnerById(ownerId);
	}


	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
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
		else {
			petHotel.setPet(pet);
			this.clinicService.savePetHotel(petHotel);
			return "redirect:/owners/{ownerId}/pets/{petId}/pet-hotels/list";
		}
	}

	@GetMapping(value = "/list")
	public String listPetHotels(@PathVariable int petId, Map<String, Object> model) {
		model.put("petHotels", this.clinicService.findPetHotelsByPetId(petId));
		List<PetHotel> p = this.clinicService.findPetHotelsByPetId(petId);
		return "pet-hotel/petHotelList";
	}

	@GetMapping(value="/delete/{petHotelId}")
	public String borrarPetHotel(@PathVariable("petHotelId") int petHotelId,@PathVariable("ownerId") int ownerId,
			@PathVariable("petId") int petId, ModelMap modelMap) {
		String view = "redirect:/owners/{ownerId}/pets/{petId}/pet-hotels/list";
		Pet pet = findPet(petId);
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
