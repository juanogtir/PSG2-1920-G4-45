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


import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final ClinicService	clinicService;

	private static final String	VIEWS_VET_CREATE_OR_UPDATE_FORM	= "vets/createOrUpdateVetForm";
	private static final String MESSAGE = "message";


	@Autowired

	private VetService vetService;
	


	public VetController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = {
		"/vets"
	})
	public String showVetList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}
	
	@GetMapping(value = { "/vets/new" })
	public String createVet(final ModelMap modelMap) {
		String view = "vets/createOrUpdateVetForm";
		modelMap.addAttribute("vet", new Vet());
		Collection<Specialty> specialties = this.clinicService.getAllSpecialties();
		modelMap.addAttribute("specialties", specialties);
		return view;
	}
	
	@PostMapping(value = { "/vets/new" })
	public String saveVet(final Integer[] specialties, @Valid final Vet vet, final BindingResult result, final Map<String, Object> modelMap) {
		String view = "vets/vetList";
		if(result.hasErrors()) {
			modelMap.put("vet", vet);
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		}
		else {
			if(specialties!=null) {
				for(Integer i : specialties) {
					vet.addSpecialty(this.clinicService.findSpecialtyById(i));
				}
			}
			clinicService.saveVet(vet);
			modelMap.put(MESSAGE, "Vet successfully saved!");
			view=showVetList(modelMap);
		}
		return view;
	}

	@GetMapping(value = {
		"/vets.xml"
	})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		return vets;
	}


	@GetMapping(path="/vets/delete/{vetId}")
	public String borrarVeterinario(@PathVariable("vetId") int vetId, ModelMap modelMap) {
		String view = "redirect:/vets";
		 Vet vet = this.vetService.findOwnerbyId(vetId);
		 if(vet!=null) {
			 this.vetService.removeVet(vet);	
			 modelMap.addAttribute(MESSAGE,"Vet succesfully deleted!");
		 }else {
			 modelMap.addAttribute(MESSAGE,"Vet not found!");
		 }
		 return view;
	}

	

	

	

	/**
	 * Custom handler for displaying a vet.
	 *
	 * @param vetId
	 *            the ID of the vet to display
	 * @return a ModelMap with the model attributes for the view
	 */
	


	@ModelAttribute("specialties")
	public Collection<Specialty> putSpecialities() {
		return this.clinicService.findVetSpecialities();
	}

	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateVetForm(@PathVariable("vetId") final int vetId, final ModelMap model) {
		Vet vet = this.clinicService.findVetById(vetId);
		model.put("vet", vet);

		return VetController.VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateVetForm(@Valid final Vet vet, final BindingResult result, @PathVariable("vetId") final int vetId, final ModelMap model, @RequestParam(required = false) final Integer[] specialties) {
		if (result.hasErrors()) {
			model.put("vet", vet);
			return VetController.VIEWS_VET_CREATE_OR_UPDATE_FORM;
		} else {
			if (specialties != null) {
				vet.deleteAllSpecialties();
				for(Integer i : specialties) {
					vet.addSpecialty(this.clinicService.findSpecialtyById(i));
				}

			}
			this.clinicService.saveVet(vet);
			return "redirect:/vets/{vetId}";
		}
	}

	/**
	 * Custom handler for displaying a vet.
	 *
	 * @param vetId
	 *            the ID of the vet to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/vets/{vetId}")
	public ModelAndView showVet(@PathVariable("vetId") final int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetDetails");
		mav.addObject(this.clinicService.findVetById(vetId));
		return mav;
	}

}
