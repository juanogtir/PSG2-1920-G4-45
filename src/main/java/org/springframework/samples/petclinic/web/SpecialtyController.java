
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class SpecialtyController {

	private final ClinicService clinicService;


	@Autowired
	public SpecialtyController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = {
		"/specialties"
	})
	public String showSpecialtiesList(final Map<String, Object> model) {
		Collection<Specialty> specialties = this.clinicService.getAllSpecialties();
		model.put("specialties", specialties);
		return "specialties/specialtyList";
	}

	@GetMapping(value = "/specialties/{specialtyId}/delete")
	public String deleteById(@PathVariable("specialtyId") final int specialtyId, final Map<String, Object> model) {
		this.clinicService.deleteSpecialtyById(specialtyId);
		return "redirect:/specialties";
	}
}
