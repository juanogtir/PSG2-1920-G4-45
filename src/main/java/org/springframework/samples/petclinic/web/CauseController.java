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

import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class CauseController {

	private final CauseService causeService;

	//	private static final String	VIEWS_CAUSE_CREATE_OR_UPDATE_FORM	= "causes/createOrUpdateCauseForm";


	public CauseController(final CauseService causeService) {
		this.causeService = causeService;
	}

	@GetMapping(value = {
		"/causes"
	})
	public String showCauseList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Collection<Cause> causes = this.causeService.findCauses();
		//for(Cause c : causes) {

		//	c.setTotalAmountOfDonations(causeService.totalAmountOfDonationsForCause(c.getId()));
		//}
		model.put("causes", causes);
		return "causes/causeList";
	}

	//	@GetMapping(value = {
	//		"/causes.xml"
	//	})
	//	public @ResponseBody Collection<Cause> showResourcesCauseList() {
	//		// Here we are returning an object of type 'Vets' rather than a collection of Vet
	//		// objects
	//		// so it is simpler for JSon/Object mapping
	//		Collection<Cause> causes = this.causeService.findCauses();
	//		return causes;
	//	}

	@GetMapping("/causes/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") final int causeId) {
		ModelAndView mav = new ModelAndView("causes/causeDetails");
		Cause cause = this.causeService.findCausebyId(causeId);
		//Integer total = this.causeService.totalAmountOfDonationsForCause(causeId);
		//cause.setTotalAmountOfDonations(total);
		mav.addObject(cause);
		return mav;
	}

}
