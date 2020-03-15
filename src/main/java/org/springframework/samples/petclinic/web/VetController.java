/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final ClinicService clinicService;

	@Autowired
	public VetController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = { "/vets" })
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
			return "vets/createOrUpdateVetForm";
		}
		else {
			if(specialties!=null) {
				for(Integer i : specialties) {
					vet.addSpecialty(this.clinicService.findSpecialtyById(i));
				}
			}
			clinicService.saveVet(vet);
			modelMap.put("message", "Vet successfully saved!");
			view=showVetList(modelMap);
		}
		return view;
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		return vets;
	}
	
	@ModelAttribute("specialties")
	public Collection<Specialty> putSpecialities() {
		return this.clinicService.findVetSpecialities();
	}
	
	@GetMapping("/vets/{vetId}")
	public ModelAndView showVet(@PathVariable("vetId") final int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetDetails");
		mav.addObject(this.clinicService.findVetById(vetId));
		return mav;
	}


}
